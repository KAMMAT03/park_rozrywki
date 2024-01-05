import React from "react";
import Nav from "../components/Nav";

export default function Tables() {
  const [tableData, setTableData] = React.useState([]);

  const [chooseView, setChooseView] = React.useState(true);

  function generateTable() {
    if (tableData.length === 0) {
      return (
        <div>
          <p>Brak danych do wyświetlenia</p>
        </div>
      );
    }

    const headers = Object.keys(tableData[0]).map((header) => {
      return (
        <th className=" bg-slate-200 p-3 font-bold text-slate-800">{header}</th>
      );
    });

    const firstRow = <tr>{headers}</tr>;

    const rows = tableData.map((row) => {
      const values = Object.values(row).map((value) => {
        return <td className="p-3">{value}</td>;
      });

      return <tr className="bg-[#8D86C9] even:bg-[#725AC1]">{values}</tr>;
    });

    return (
      <table className="m-4 -ml-8 w-full border-collapse text-left text-sm text-slate-200 shadow-xl">
        <thead>{firstRow}</thead>
        <tbody>{rows}</tbody>
      </table>
    );
  }

  React.useEffect(() => {
    fetch(`http://localhost:8080/api/klienci/getall?pageSize=100`)
      .then((res) => res.json())
      .then((data) => setTableData(data.content));

    console.log(tableData);
  }, []);

  function changeTable(type) {
    fetch(`http://localhost:8080/api/${type}/getall?pageSize=100`)
      .then((res) => res.json())
      .then((data) => setTableData(data.content));
  }

  return (
    <>
      <Nav />
      <main className="mt-24 flex p-6 pl-0">
        <div className="-mt-6 flex h-14">
          {chooseView && <div className="mt-0 w-48"></div>}
          <div className="relative mt-0 w-14 rounded-br-lg bg-[#725AC1] shadow-xl">
            <img
              src="tribar.jpg"
              className="linear absolute right-3 top-3 size-8 cursor-pointer transition duration-200 hover:opacity-75 active:opacity-50"
              onClick={() => setChooseView(!chooseView)}
              alt="close button"
            />
          </div>
        </div>
        {chooseView && (
          <div
            className=" fixed bottom-0 left-0 top-24 flex w-48 flex-col gap-3
                 bg-[#725AC1] p-2 font-bold text-slate-200"
            id="choose"
          >
            <button
              onClick={() => changeTable("atrakcje")}
              className=" linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75"
            >
              Atrakcje
            </button>
            <button
              className=" linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75"
              onClick={() => changeTable("kolejki")}
            >
              Kolejki górskie
            </button>
            <button
              className=" linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75"
              onClick={() => changeTable("gastro")}
            >
              Gastronomie
            </button>
            <button
              className=" linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75"
              onClick={() => changeTable("pracownicy")}
            >
              Pracownicy
            </button>
            <button
              className=" linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75"
              onClick={() => changeTable("stanowiska")}
            >
              Stanowiska
            </button>
            <button
              className=" linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75"
              onClick={() => changeTable("klienci")}
            >
              Klienci
            </button>
            <button
              className=" linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75"
              onClick={() => changeTable("typybiletow")}
            >
              Typy biletów
            </button>
            <button
              className=" linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75"
              onClick={() => changeTable("bilety")}
            >
              Bilety
            </button>
            <button
              className=" linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75"
              onClick={() => changeTable("parking")}
            >
              Miejsca parkingowe
            </button>
            <button
              className=" linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75"
              onClick={() => changeTable("parking/rodzaje")}
            >
              Rodzaje miejsc parkingowych
            </button>
          </div>
        )}
        <div className="flex w-full flex-col gap-2">
          <button className="ml-[43vw] w-24 rounded-lg bg-slate-200 px-4 py-2 text-lg font-bold text-slate-800 transition duration-200 ease-linear hover:opacity-85 active:opacity-75">
            Dodaj
          </button>
          {generateTable()}
        </div>
      </main>
    </>
  );
}
