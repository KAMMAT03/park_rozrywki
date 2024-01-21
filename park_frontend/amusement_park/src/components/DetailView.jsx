import React from "react";

export default function DetailView(props) {
  return (
    <div
      className="fixed left-[calc(50vw-290px)] right-[calc(50vw-290px)] top-[calc(50vh-288px)] z-20 flex flex-col gap-2 rounded-xl bg-[#CAC4CE] p-8 text-slate-800"
      id="dets"
    >
      <img
        src="x-svg.svg"
        className="linear absolute right-1 top-1 size-12 cursor-pointer transition duration-200 hover:opacity-75 active:opacity-50"
        onClick={props.hideDetails}
        alt="close button"
      />
      <img
        className=" mx-auto h-80 w-96 rounded-md border-2 border-slate-800 object-cover shadow-lg"
        src={props.element.imageUrl}
        alt="kolejka"
      />
      <p className="border-t-0 border-slate-800 text-center  text-xl font-bold">
        {props.element.nazwaAtrakcji}
      </p>
      <p className="text-center">{props.element.opisAtrakcji}</p>
      {props.type === "kolejka" ? (
        <div className="grid grid-cols-2 gap-2 border-t-2 border-[#CAC4CE] p-2">
          <p>
            Maksymalna wysokosc:{" "}
            <strong>{props.element.maksymalnaWysokosc} m</strong>
          </p>
          <p>
            Minimalny wzrost:{" "}
            <strong>{props.element.minimalnyWzrost} cm</strong>
          </p>
          <p>
            Średnia prędkość:{" "}
            <strong>{props.element.sredniaPredkosc} km/h</strong>
          </p>
          <p>
            Czas jazdy: <strong>{props.element.czasJazdy} min</strong>
          </p>
          <p>
            Pojemność wagonika:{" "}
            <strong>{props.element.pojemnoscWagonika} osób</strong>
          </p>
          <p>
            Minimalny wiek: <strong>{props.element.minimialnyWiek} lat</strong>
          </p>
        </div>
      ) : (
        <div className="flex flex-col items-center gap-2 border-t-0 p-2">
          <p>
            Typ kuchni: <strong>{props.element.typ}</strong>
          </p>
          <p>
            Godziny otwarcia: <strong>{props.element.godzinyOtwarcia}</strong>
          </p>
          <p>
            Liczba miejsc: <strong>{props.element.liczbaMiejsc}</strong>
          </p>
        </div>
      )}
    </div>
  );
}
