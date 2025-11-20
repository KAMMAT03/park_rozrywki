import React from "react";
import Nav from "../components/Nav";
import { useLocation, useNavigate } from "react-router-dom";
import { BACKEND_URL } from "../App";

export default function Account() {
  const [tickets, setTickets] = React.useState([]);
  const location = useLocation();
  const navigate = useNavigate();
  const [accountData, setAccountData] = React.useState({});

  React.useEffect(() => {
    if (!location.state) {
      navigate("/main");
      return;
    }

    fetch(`${BACKEND_URL}/api/klienci/get`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${location.state.token}`,
      },
    })
      .then((res) => res.json())
      .then((data) =>
        setAccountData({ ...data, username: location.state.username }),
      )
      .catch((err) => {
        console.log("Internal server error. Try again later.");
      });

    fetch(`${BACKEND_URL}/api/bilety/getall/klient`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${location.state.token}`,
      },
    })
      .then((res) => res.json())
      .then((data) => {
        setTickets(data.content);
      })
      .catch((err) => {
        console.log("Internal server error. Try again later.");
      });
  }, []);

  const ticketsElements = tickets.map((ticket) => {
    return (
      <div
        className="flex justify-between rounded-2xl p-6 shadow-xl"
        id="bilet"
      >
        <div className="flex w-[65%] flex-col gap-2 text-wrap text-slate-200">
          <p className="text-3xl font-bold">{ticket.standard}</p>
          <p className="border-b-2 border-[#CAC4CE] pb-3 text-xl font-semibold">
            {ticket.ulga}
          </p>
          <p className="text-lg">{ticket.opisTypuBiletu}</p>
        </div>
        <div className="flex w-[280px] flex-col items-center justify-center gap-2 rounded-xl bg-slate-200 p-4 text-slate-800 shadow-xl">
          <p>
            Data zakupu:{" "}
            <p className="inline rounded-lg bg-[#CAC4CE] px-1">
              {ticket.dataZakupu}
            </p>
          </p>
          <p>
            Data ważności:{" "}
            <p className="inline rounded-lg bg-[#CAC4CE] px-1">
              {ticket.dataWaznosci}
            </p>
          </p>
          <p>
            Cena:{" "}
            <p className="inline rounded-lg bg-[#CAC4CE] px-1">
              {ticket.cena} zł
            </p>
          </p>
        </div>
      </div>
    );
  });

  return (
    <>
      <Nav location={location.state} current="/account" />
      <main className="mt-24 flex flex-col items-center justify-center gap-4 p-10 text-slate-800">
        <div className="flex w-[80vw] justify-between rounded-xl bg-[#CAC4CE] p-8 text-lg font-medium">
          <div className="flex w-[20%] flex-col pr-4">
            <p>Username:</p>
            <p className="rounded-lg bg-slate-200 px-1">
              {accountData.username}
            </p>
            <p>Imie:</p>
            <p className="rounded-lg bg-slate-200 px-1">{accountData.imie}</p>
            <p>Nazwisko:</p>
            <p className="rounded-lg bg-slate-200 px-1">
              {accountData.nazwisko}
            </p>
            <p>Data urodzenia:</p>
            <p className="rounded-lg bg-slate-200 px-1">
              {accountData.dataUrodzenia}
            </p>
          </div>
          <div className="flex w-[80%] flex-col gap-3 border-l-2 border-slate-200 pl-4">
            <p className="-mt-3 mb-3 text-center text-xl font-bold">
              Moje bilety:
            </p>
            <div className="flex flex-col gap-3 overflow-y-auto">
              {ticketsElements}
            </div>
          </div>
        </div>
      </main>
      <img
        className="fixed bottom-0 left-0 -z-10 opacity-30"
        src="bg-image.jpg"
        alt="background image"
      />
    </>
  );
}
