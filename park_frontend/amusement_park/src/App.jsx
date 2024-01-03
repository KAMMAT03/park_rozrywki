import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Main from './pages/Main';
import Bilety from './pages/Bilety';
import Auth from './pages/Auth';
import Tables from './pages/Tables';
import './index.css'

export default function App(){
  
  return (
    <div>
      <Routes>
        <Route path="*" element={<Main />} />
        {/* <Route path="*" element={<Bilety />} /> */}
        {/* <Route path="*" element={<Auth />} /> */}
        {/* <Route path="*" element={<Tables />} /> */}
      </Routes>
    </div>
  )
}