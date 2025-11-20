import React from "react";
import { Routes, Route } from "react-router-dom";
import Main from "./pages/Main";
import Bilety from "./pages/Bilety";
import Auth from "./pages/Auth";
import Tables from "./pages/Tables";
import NotFound from "./pages/Redirect";
import Account from "./pages/Account";
import "./index.css";

export const BACKEND_URL = "https://amusementpark-api-436625947182.europe-central2.run.app"

export default function App() {
  return (
    <div>
      <Routes>
        <Route path="/main" element={<Main />} />
        <Route path="*" element={<NotFound />} />
        <Route path="/tickets" element={<Bilety />} />
        <Route path="/auth" element={<Auth />} />
        <Route path="/adminview" element={<Tables />} />
        <Route path="/account" element={<Account />} />
      </Routes>
    </div>
  );
}
