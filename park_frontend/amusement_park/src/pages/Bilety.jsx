import React from "react";
import Nav from "../components/Nav";
import { useLocation, useNavigate } from "react-router-dom";

export default function Bilety() {
  const [typyBiletow, setTypyBiletow] = React.useState([]);
  const [alertVisible, setAlertVisible] = React.useState(false);
  const location = useLocation();
  const [idTypuBiletu, setIdTypuBiletu] = React.useState();
  const [alertBuy, setAlertBuy] = React.useState(location.state);
  const navigate = useNavigate();
  const [showBuyInfo, setShowBuyInfo] = React.useState(false);

  React.useEffect(() => {
    fetch("http://localhost:8080/api/typybiletow/getall")
      .then((res) => {
        console.log(res);
        return res.json();
      })
      .then((data) => setTypyBiletow(data.content))
      .catch((err) => alert("Internal server error. Try again later."));
  }, []);

  function buyTicket(event) {
    event.preventDefault();

    fetch("http://localhost:8080/api/bilety/create", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${location.state.token}`,
      },
      body: JSON.stringify({
        idTypuBiletu: idTypuBiletu,
      }),
    })
      .then((response) => response.json())
      .then((json) => {
        setShowBuyInfo(true);
        setTimeout(() => setShowBuyInfo(false), 2000);
      })
      .catch((err) => alert("Internal server error. Try again later."));

    setAlertVisible(false);
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
            onClick={(event) => {
              setAlertVisible(true);
              alertBuy && setIdTypuBiletu(event.target.name);
            }}
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
      <Nav location={location.state} current="/tickets" />
      <main className="mt-24 flex flex-col gap-4 p-10 text-slate-200">
        {typyElements}
        {alertVisible && (
          <>
            <div className=" fixed bottom-0 left-0 right-0 top-0 z-20 bg-black opacity-65" />
            <div
              className={`fixed ${
                alertBuy ? "left-[calc(50vw-180px)]" : "left-[calc(50vw-239px)]"
              } ${
                alertBuy
                  ? "right-[calc(50vw-180px)]"
                  : "right-[calc(50vw-239px)]"
              } z-20 mt-[20vh] flex flex-col gap-2 overflow-y-hidden rounded-xl bg-[#CAC4CE] p-8 text-center text-lg font-bold text-slate-800`}
            >
              <p>
                {alertBuy
                  ? "Potwierdzasz zakup biletu?"
                  : "Aby zakupić bilet musisz się najpierw zalogować!"}
              </p>
              <div className="flex flex-col gap-3">
                <button
                  onClick={
                    alertBuy
                      ? (event) => buyTicket(event)
                      : () => navigate("/auth", { state: { prev: "/tickets" } })
                  }
                  className={`linear m-auto w-[104px] rounded-lg bg-slate-200 py-1 font-bold
                        text-slate-800 shadow-xl transition duration-200 hover:opacity-85 active:opacity-75 ${
                          alertBuy && "hover:text-[#03C03C]"
                        }`}
                >
                  {alertBuy ? "Tak" : "Zaloguj się"}
                </button>
                <button
                  onClick={() => setAlertVisible(false)}
                  className="linear m-auto rounded-lg bg-slate-200 bg-opacity-80 px-6 py-1 font-bold text-slate-800
                        shadow-xl transition duration-200 hover:text-[#FF033E] hover:opacity-85 active:opacity-75"
                >
                  Anuluj
                </button>
              </div>
            </div>
          </>
        )}
        {showBuyInfo && (
          <div className="fixed left-[calc(50vw-140px)] right-[calc(50vw-140px)] top-28 rounded-xl bg-[#03C03C] p-1 text-center text-lg font-bold transition duration-150">
            Bilet został zakupiony
          </div>
        )}
      </main>
      <img
        className="fixed bottom-0 left-0 -z-10 opacity-30"
        src="bg-image.jpg"
        alt="background image"
      />
    </>
  );
}
