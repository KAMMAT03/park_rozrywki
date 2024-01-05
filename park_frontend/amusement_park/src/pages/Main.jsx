import React from "react";
import Nav from "../components/Nav";
import DetailView from "../components/DetailView";

export default function Main() {
  const [kolejki, setKolejki] = React.useState([]);
  const [gastronomie, setGastronomie] = React.useState([]);
  const [atrakcje, setAtrakcje] = React.useState([]);
  const [detailView, setDetailView] = React.useState();
  const [detailViewActive, setDetailViewActive] = React.useState(false);

  React.useEffect(() => {
    fetch("http://localhost:8080/api/kolejki/getall")
      .then((res) => res.json())
      .then((data) => setKolejki(data.content));

    fetch("http://localhost:8080/api/gastro/getall")
      .then((res) => res.json())
      .then((data) => setGastronomie(data.content));

    fetch("http://localhost:8080/api/atrakcje/getonly")
      .then((res) => res.json())
      .then((data) => setAtrakcje(data.content));
  }, []);

  function mapToDiv(element, type) {
    const onClick =
      type === "atrakcja" ? () => {} : () => showDetails(element, type);

    return (
      <div
        className=" flex cursor-pointer flex-col gap-3 rounded-xl p-6 hover:opacity-90"
        id="atr"
        onClick={onClick}
      >
        <img
          className=" rounded-md object-cover"
          src="coaster.jpg"
          alt={element.typAtrakcji}
        />
        <p className="text-xl font-bold">{element.nazwaAtrakcji}</p>
        <p>{element.opisAtrakcji}</p>
      </div>
    );
  }

  function showDetails(element, type) {
    setDetailViewActive(true);
    setDetailView(
      <DetailView hideDetails={hideDetails} element={element} type={type} />,
    );
  }

  function hideDetails() {
    setDetailViewActive(false);
    setDetailView();
  }

  const kolejkiElements = kolejki.map((kolejka) => {
    return mapToDiv(kolejka, "kolejka");
  });

  const gastronomieElements = gastronomie.map((gastronomia) => {
    return mapToDiv(gastronomia, "gastronomia");
  });

  const atrakcjeElements = atrakcje.map((atrakcja) => {
    return mapToDiv(atrakcja, "atrakcja");
  });

  return (
    <>
      <Nav />
      <main className=" mt-24 p-10">
        <h1 className=" text-center text-2xl font-bold text-slate-200">
          Kolejki górskie:
        </h1>
        <div className="mt-4 grid gap-4 border-b-2 border-[#CAC4CE] pb-8 text-slate-200 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
          {kolejkiElements}
        </div>
        <h1 className=" mt-4 text-center text-2xl font-bold text-slate-200">
          Gastronomie:
        </h1>
        <div className="mt-4 grid gap-4 border-b-2 border-[#CAC4CE] pb-8 text-slate-200 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
          {gastronomieElements}
        </div>
        <h1 className=" mt-4 text-center text-2xl font-bold text-slate-200">
          Inne atrakcje:
        </h1>
        <div className="mt-4 grid gap-4 text-slate-200 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3">
          {atrakcjeElements}
        </div>
      </main>
      {detailViewActive && (
        <>
          <div className=" fixed bottom-0 left-0 right-0 top-0 z-20 bg-black opacity-65"></div>
          {detailView}
        </>
      )}
      <img
        className="fixed bottom-0 left-0 -z-10 opacity-30"
        src="bg-image.jpg"
        alt="background image"
      />
    </>
  );
}
