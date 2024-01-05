import React from "react";
import Nav from "../components/Nav";

export default function Bilety() {
  const [typyBiletow, setTypyBiletow] = React.useState([]);

  React.useEffect(() => {
    fetch("http://localhost:8080/api/typybiletow/getall")
      .then((res) => res.json())
      .then((data) => setTypyBiletow(data.content));
  }, []);

  function buyTicket(event) {
    event.preventDefault();

    fetch("http://localhost:8080/api/bilety/create/1", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        idTypuBiletu: event.target.name,
      }),
    })
      .then((response) => response.json())
      .then((json) => {
        console.log(json);
        // if (json.code === '200'){
        //     navigate("/search", { state: {username: credentials.username, token: json.token} });
        // } else {
        //     setMessage(json.message);
        //     setCredentials(prev => ({
        //         ...prev,
        //         password: "",
        //         confpassword: ""
        //     }));
        // }
      });
  }

  const typyElements = typyBiletow.map((typ) => {
    return (
      <div
        className="flex justify-between rounded-2xl p-6 shadow-xl"
        id="bilet"
      >
        <div className="flex w-max flex-col gap-2">
          <p className="text-3xl font-bold">{typ.standard}</p>
          <p className="border-b-2 border-[#CAC4CE] pb-3 text-xl font-semibold">
            {typ.ulga}
          </p>
          <p className="text-lg">{typ.opisTypuBiletu}</p>
        </div>
        <div className="flex min-w-48 flex-col items-center justify-center gap-4 rounded-xl bg-slate-200 p-4 text-slate-800 shadow-xl">
          <p className="text-3xl font-bold">{typ.cena} zł</p>
          <button
            name={typ.idTypuBiletu}
            onClick={buyTicket}
            className="rounded-lg bg-[#CAC4CE] px-6 py-2 text-xl font-bold shadow-xl transition duration-200 hover:opacity-85 active:opacity-75"
          >
            Kup
          </button>
        </div>
      </div>
    );
  });

  return (
    <>
      <Nav />
      <main className="mt-24 flex flex-col gap-4 p-10 text-slate-200">
        {typyElements}
      </main>
      <img
        className="fixed bottom-0 left-0 -z-10 opacity-30"
        src="bg-image.jpg"
        alt="background image"
      />
    </>
  );
}
