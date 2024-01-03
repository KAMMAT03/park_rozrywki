import React from "react";

export default function Nav() {
    return (
        <nav className=" fixed top-0 left-0 right-0 flex gap-4 bg-emerald-700 h-24 items-center p-8 shadow-md">
            <p className=" mr-auto text-slate-200 font-bold text-xl">Adrenaline City</p>
            <button
                className=" bg-slate-200 text-emerald-700 px-2 py-1 font-bold rounded-lg hover:opacity-85
                transition duration-200 linear active:opacity-75"
            >
                Bilety
            </button>
            <button
                className=" bg-slate-200 text-emerald-700 px-2 py-1 font-bold rounded-lg hover:opacity-85
                transition duration-200 linear active:opacity-75"
            >
                Parking
            </button>
            <button
                className=" bg-slate-200 text-emerald-700 px-2 py-1 font-bold rounded-lg hover:opacity-85
                transition duration-200 linear active:opacity-75"
            >
                Zaloguj
            </button>
        </nav>
    )
}