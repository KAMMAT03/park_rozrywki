import React from "react";
import "../styles/style.css";

export default function Nav() {
  return (
    <nav
      className="fixed left-0 right-0 top-0 z-10 flex h-24 items-center gap-4 bg-[#725AC1] p-8 shadow-md"
      id="nav"
    >
      <p className=" mr-auto text-2xl font-bold text-slate-200">
        Adrenaline City
      </p>
      <button
        className=" linear rounded-lg bg-slate-200 px-2 py-1 font-bold text-slate-800
                shadow-xl transition duration-200 hover:opacity-85 active:opacity-75"
      >
        Bilety
      </button>
      <button
        className=" linear rounded-lg bg-slate-200 px-2 py-1 font-bold text-slate-800
                shadow-xl transition duration-200 hover:opacity-85 active:opacity-75"
      >
        Parking
      </button>
      <button
        className=" linear rounded-lg bg-slate-200 px-2 py-1 font-bold text-slate-800
                shadow-xl transition duration-200 hover:opacity-85 active:opacity-75"
      >
        Zaloguj
      </button>
    </nav>
  );
}
