package com.metro.backend.algorithm;

import com.metro.backend.cache.MetroDataCache;
import com.metro.backend.dto.Itinerary;
import com.metro.backend.dto.Node;
import com.metro.backend.dto.RouteResponse;
import com.metro.backend.entity.sqlite.Station;
import com.metro.backend.graph.Edge;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Component
public class Dijkstra {

    private final MetroDataCache metroDataCache;


    public Dijkstra(MetroDataCache metroDataCache) {

        this.metroDataCache =
                metroDataCache;
    }


    public RouteResponse findShortestPath(
            String source,
            String destination) {


        // =================================================
        // 1. Get all possible station IDs
        // =================================================

        List<Integer> sourceStationIds =
                metroDataCache.getStationIdByName(
                        source
                );

        List<Integer> destinationStationIds =
                metroDataCache.getStationIdByName(
                        destination
                );


        // =================================================
        // 2. Validate source and destination
        // =================================================

        if (sourceStationIds.isEmpty()) {

            throw new IllegalArgumentException(
                    "Source station not found: " + source
            );
        }


        if (destinationStationIds.isEmpty()) {

            throw new IllegalArgumentException(
                    "Destination station not found: "
                            + destination
            );
        }


        // =================================================
        // 3. Dijkstra data structures
        // =================================================

        /*
         * distance[stationId]
         *
         * Shortest travel time from ANY source
         * station to this station.
         */
        Map<Integer, Integer> distance =
                new HashMap<>();


        /*
         * previous[stationId]
         *
         * Previous station in the shortest path.
         */
        Map<Integer, Integer> previous =
                new HashMap<>();


        /*
         * previousEdge[stationId]
         *
         * Edge used to reach this station.
         *
         * This is important for:
         *
         * - fare
         * - interchange detection
         * - transfer information
         */
        Map<Integer, Edge> previousEdge =
                new HashMap<>();


        /*
         * Priority Queue ordered by travel time.
         */
        PriorityQueue<Node> pq =
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                Node::getTime
                        )
                );


        // =================================================
        // 4. Multi-source initialization
        // =================================================

        /*
         * If source has multiple IDs:
         *
         * Esplanade Blue   -> 101
         * Esplanade Green  -> 102
         *
         * Both are valid starting points.
         */
        for (Integer sourceId : sourceStationIds) {

            distance.put(
                    sourceId,
                    0
            );

            pq.offer(
                    new Node(
                            sourceId,
                            0
                    )
            );
        }


        // =================================================
        // 5. Dijkstra
        // =================================================

        while (!pq.isEmpty()) {

            Node current =
                    pq.poll();


            int currentStationId =
                    current.getStationId();


            int currentTime =
                    current.getTime();


            /*
             * Ignore stale priority queue entries.
             *
             * Example:
             *
             * station 105 was inserted with 20 minutes.
             *
             * Later we found:
             *
             * station 105 = 10 minutes.
             *
             * The old 20-minute entry is still in
             * the priority queue.
             *
             * We skip it here.
             */
            if (currentTime !=
                    distance.getOrDefault(
                            currentStationId,
                            Integer.MAX_VALUE
                    )) {

                continue;
            }


            // Get neighbouring stations

            List<Edge> neighbours =
                    metroDataCache.getNeighbours(
                            currentStationId
                    );


            // -------------------------------------------------
            // Relax all edges
            // -------------------------------------------------

            for (Edge edge : neighbours) {

                int nextStationId =
                        edge.getDestinationStationId();


                int newTime =
                        currentTime
                                + edge.getTravelTime();


                int oldTime =
                        distance.getOrDefault(
                                nextStationId,
                                Integer.MAX_VALUE
                        );


                /*
                 * Found a shorter path.
                 */
                if (newTime < oldTime) {

                    distance.put(
                            nextStationId,
                            newTime
                    );


                    /*
                     * Store previous station.
                     */
                    previous.put(
                            nextStationId,
                            currentStationId
                    );


                    /*
                     * Store edge used.
                     */
                    previousEdge.put(
                            nextStationId,
                            edge
                    );


                    /*
                     * Add updated node.
                     */
                    pq.offer(
                            new Node(
                                    nextStationId,
                                    newTime
                            )
                    );
                }
            }
        }


        // =================================================
        // 6. Find best destination ID
        // =================================================

        Integer bestDestinationId =
                null;


        int bestTime =
                Integer.MAX_VALUE;


        /*
         * A station name can have multiple IDs.
         *
         * Example:
         *
         * Howrah Blue   -> 201
         * Howrah Green  -> 202
         *
         * Pick whichever is reachable in minimum time.
         */
        for (Integer destinationId :
                destinationStationIds) {

            int time =
                    distance.getOrDefault(
                            destinationId,
                            Integer.MAX_VALUE
                    );


            if (time < bestTime) {

                bestTime = time;

                bestDestinationId =
                        destinationId;
            }
        }


        // =================================================
        // 7. No route found
        // =================================================

        if (bestDestinationId == null ||
                bestTime == Integer.MAX_VALUE) {

            throw new IllegalArgumentException(
                    "No route found from "
                            + source
                            + " to "
                            + destination
            );
        }


        // =================================================
        // 8. Reconstruct shortest path
        // =================================================

        List<Integer> path =
                reconstructPath(
                        bestDestinationId,
                        previous
                );


        // =================================================
        // 9. Calculate fare and interchanges
        // =================================================

        int totalFare =
                calculateTotalFare(
                        path,
                        previousEdge
                );


        int interchangeCount =
                calculateInterchangeCount(
                        path,
                        previousEdge
                );


        // =================================================
        // 10. Build itinerary
        // =================================================

        List<Itinerary> itinerary =
                buildItinerary(
                        path,
                        previousEdge
                );


        // =================================================
        // 11. Return response
        // =================================================

        return new RouteResponse(
                source,
                destination,
                bestTime,
                totalFare,
                interchangeCount,
                itinerary
        );
    }


    // =====================================================
    // Reconstruct path
    // =====================================================

    private List<Integer> reconstructPath(
            Integer destinationId,
            Map<Integer, Integer> previous) {


        List<Integer> path =
                new ArrayList<>();


        Integer current =
                destinationId;


        while (current != null) {

            path.add(current);

            current =
                    previous.get(current);
        }


        /*
         * We reconstructed backwards:
         *
         * Destination
         *      ↓
         * Previous
         *      ↓
         * Previous
         *
         * Reverse it.
         */
        Collections.reverse(path);


        return path;
    }


    // =====================================================
    // Calculate total fare
    // =====================================================

    private int calculateTotalFare(
            List<Integer> path,
            Map<Integer, Edge> previousEdge) {


        int totalFare = 0;


        /*
         * First station has no incoming edge.
         *
         * Therefore start from index 1.
         */
        for (int i = 1;
             i < path.size();
             i++) {


            int currentStationId =
                    path.get(i);


            Edge edge =
                    previousEdge.get(
                            currentStationId
                    );


            if (edge != null) {

                totalFare +=
                        edge.getFare();
            }
        }


        return totalFare;
    }


    // =====================================================
    // Calculate interchange count
    // =====================================================

    private int calculateInterchangeCount(
            List<Integer> path,
            Map<Integer, Edge> previousEdge) {


        int interchangeCount = 0;


        for (int i = 1;
             i < path.size();
             i++) {


            int currentStationId =
                    path.get(i);


            Edge edge =
                    previousEdge.get(
                            currentStationId
                    );


            if (edge != null &&
                    edge.isInterchange()) {

                interchangeCount++;
            }
        }


        return interchangeCount;
    }


    // =====================================================
    // Build itinerary
    // =====================================================

    private List<Itinerary> buildItinerary(
            List<Integer> path,
            Map<Integer, Edge> previousEdge) {


        List<Itinerary> itinerary =
                new ArrayList<>();


        for (int i = 0;
             i < path.size();
             i++) {


            int stationId =
                    path.get(i);


            Station station =
                    metroDataCache.getStation(
                            stationId
                    );


            if (station == null) {
                continue;
            }


            String stationName =
                    station.getName();


            String line =
                    station.getLine();


            boolean isInterchange =
                    false;


            String transferTo =
                    null;


            /*
             * To determine whether we are
             * transferring FROM this station,
             * look at the edge going to the
             * next station.
             */
            if (i < path.size() - 1) {


                int nextStationId =
                        path.get(i + 1);


                Edge nextEdge =
                        findEdge(
                                stationId,
                                nextStationId
                        );


                if (nextEdge != null &&
                        nextEdge.isInterchange()) {


                    isInterchange = true;


                    Station nextStation =
                            metroDataCache.getStation(
                                    nextStationId
                            );


                    if (nextStation != null) {

                        transferTo =
                                nextStation.getLine();
                    }
                }
            }


            itinerary.add(
                    new Itinerary(
                            stationName,
                            line,
                            isInterchange,
                            transferTo
                    )
            );
        }


        return itinerary;
    }


    // =====================================================
    // Find edge between two stations
    // =====================================================

    private Edge findEdge(
            int sourceId,
            int destinationId) {


        List<Edge> neighbours =
                metroDataCache.getNeighbours(
                        sourceId
                );


        for (Edge edge : neighbours) {


            if (edge.getDestinationStationId()
                    == destinationId) {


                return edge;
            }
        }


        return null;
    }
}