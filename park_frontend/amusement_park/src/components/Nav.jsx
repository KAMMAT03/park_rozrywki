import React from "react";
import "../styles/style.css";
import { useNavigate } from "react-router-dom";

export default function Nav(props) {
  const navigate = useNavigate();

  function goToLogin() {
    navigate("/auth");
  }

  return (
    <nav
      className="fixed left-0 right-0 top-0 z-10 flex h-24 items-center gap-4 bg-[#725AC1] p-8 shadow-md"
      id="nav"
    >
      <button
        onClick={() =>
          props.location?.username
            ? navigate("/main", {
                state: {
                  username: props.location.username,
                  token: props.location.token,
                  role: props.location.role,
                },
              })
            : navigate("/main")
        }
        className=" mr-auto text-2xl font-bold text-slate-200"
      >
        Adrenaline City
      </button>
      {props.location?.role === "ADMIN" && (
        <button
          onClick={() =>
            navigate("/adminview", {
              state: {
                username: props.location.username,
                token: props.location.token,
                role: props.location.role,
              },
            })
          }
          className=" linear rounded-lg bg-slate-200 px-2 py-1 font-bold text-slate-800
                shadow-xl transition duration-200 hover:opacity-85 active:opacity-75"
        >
          Widok admina
        </button>
      )}
      <button
        onClick={() =>
          props.location?.username
            ? navigate("/tickets", {
                state: {
                  username: props.location.username,
                  token: props.location.token,
                  role: props.location.role,
                },
              })
            : navigate("/tickets")
        }
        className=" linear rounded-lg bg-slate-200 px-2 py-1 font-bold text-slate-800
                shadow-xl transition duration-200 hover:opacity-85 active:opacity-75"
      >
        Bilety
      </button>
      {props.location && (
        <button
          onClick={() =>
            navigate("/account", {
              state: {
                username: props.location.username,
                token: props.location.token,
                role: props.location.role,
              },
            })
          }
          className=" linear rounded-lg bg-slate-200 px-2 py-1 font-bold text-slate-800
                shadow-xl transition duration-200 hover:opacity-85 active:opacity-75"
        >
          Moje konto
        </button>
      )}
      <button
        onClick={
          props.location?.username
            ? () => {
                props.current === "/adminview"
                  ? navigate("/main")
                  : navigate(props.current);
                window.location.reload();
              }
            : () => navigate("/auth", { state: { prev: props.current } })
        }
        className=" linear rounded-lg bg-slate-200 px-2 py-1 font-bold text-slate-800
                shadow-xl transition duration-200 hover:opacity-85 active:opacity-75"
      >
        {props.location?.username ? "Wyloguj się" : "Zaloguj się"}
      </button>
    </nav>
  );
}
