import React, { useState, useEffect } from 'react';
import { getSystemStatus } from '../services/api';
import { CheckCircle2, XCircle, RefreshCw, Key, Database, Activity, ShieldAlert } from 'lucide-react';

export default function SystemStatus({ onStatusVerified }) {
  const [statusData, setStatusData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [lastChecked, setLastChecked] = useState(null);

  const checkStatus = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await getSystemStatus();
      setStatusData(response.data);
      if (response.data === true) {
        onStatusVerified(true);
      } else {
        onStatusVerified(false);
      }
    } catch (err) {
      console.error(err);
      setError('System Error - Verification Offline');
      setStatusData(null);
      onStatusVerified(false);
    } finally {
      setLoading(false);
      setLastChecked(new Date().toLocaleTimeString());
    }
  };

  useEffect(() => {
    checkStatus();
    // Poll system status every 15 seconds to track heartbeat and updates
    const interval = setInterval(checkStatus, 15000);
    return () => clearInterval(interval);
  }, []);

  const isOperational = statusData && statusData.status === 'fully_operational';

  return (
    <div className='mb-8'>
      
    </div>
  );
}
