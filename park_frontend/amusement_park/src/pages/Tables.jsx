import React from "react";
import Nav from "../components/Nav";
import { useLocation, useNavigate } from "react-router-dom";

export default function Tables() {
  const [tableData, setTableData] = React.useState([]);

  const [chooseView, setChooseView] = React.useState(true);

  const [currentTable, setCurrentTable] = React.useState("");

  const [addElements, setAddElements] = React.useState([]);

  const navigate = useNavigate();
  const location = useLocation();
  const [showEdit, setShowEdit] = React.useState(false);
  const [editId, setEditId] = React.useState();
  const [editContent, setEditContent] = React.useState("");
  const [editName, setEditName] = React.useState("");
  const [dataObject, setObject] = React.useState({});
  const [showAdd, setShowAdd] = React.useState(false);
  const [showDelete, setShowDelete] = React.useState(false);
  const [deleteId, setDeleteId] = React.useState();

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

    headers.push(
      <th className=" bg-slate-200 p-3 font-bold text-slate-800"></th>,
    );

    const firstRow = <tr>{headers}</tr>;

    const rows = tableData.map((row) => {
      const values = Object.values(row).map((value, n) => {
        return (
          <td className="p-3">
            <button
              onClick={() => {
                if (n === 0) return;
                setEditId(Object.values(row)[0]);
                setEditContent(value);
                setEditName(Object.keys(row)[n]);
                setShowEdit(true);
              }}
              className={`linear rounded-lg px-2 py-1 text-left font-medium ${
                n
                  ? "active::opacity-85 cursor-pointer transition duration-200 hover:bg-slate-200 hover:text-slate-800 hover:shadow-xl"
                  : "cursor-default"
              } ${Object.keys(row)[n] === "imageUrl" && "w-48 break-all"}`}
            >
              {value}
            </button>
          </td>
        );
      });

      values.push(
        <td className="pr-2">
          <button
            onClick={() => {
              setDeleteId(Object.values(row)[0]);
              setShowDelete(true);
            }}
            className=" flex flex-shrink-0 items-center justify-center"
          >
            <img
              src="trash-icon.svg"
              alt="delete icon"
              className="-mr-3 size-10 flex-shrink-0 cursor-pointer transition duration-200 hover:opacity-75 active:opacity-50"
            />
          </button>
        </td>,
      );

      return (
        <tr
          className="bg-[#8D86C9] even:bg-[#725AC1]"
          key={Object.values(row)[0]}
        >
          {values}
        </tr>
      );
    });

    return (
      <table className="m-4 mt-14 w-[97%] border-collapse text-left text-sm text-slate-200 shadow-xl">
        <thead>{firstRow}</thead>
        <tbody>{rows}</tbody>
      </table>
    );
  }

  React.useEffect(() => {
    if (!location.state || location.state?.role !== "ADMIN") {
      location.state
        ? navigate("/main", {
            state: {
              username: location.state.username,
              token: location.state.token,
              role: location.state.role,
            },
          })
        : navigate("/main");
      return;
    }

    fetch(`http://localhost:8080/api/atrakcje/getall?pageSize=100`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${location.state.token}`,
      },
    })
      .then((res) => res.json())
      .then((data) => {
        setCurrentTable("atrakcje");
        setTableData(data.content);
      })
      .catch((err) => alert("Internal server error. Try again later."));
  }, []);

  function changeTable(type) {
    fetch(`http://localhost:8080/api/${type}/getall?pageSize=100`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${location.state.token}`,
      },
    })
      .then((res) => res.json())
      .then((data) => {
        setTableData(data.content);
        setObject({});
        setAddElements([]);
        setCurrentTable(type);
      })
      .catch((err) => alert("Internal server error. Try again later."));
  }

  function deleteElement(event) {
    event.preventDefault();

    fetch(`http://localhost:8080/api/${currentTable}/delete/${deleteId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${location.state.token}`,
      },
    })
      .then((res) => res.json())
      .then(() => alert("Usunięto element"))
      .catch((err) => alert("Internal server error. Try again later."));

    setTimeout(() => window.location.reload(), 300);
  }

  function updateTable(event) {
    event.preventDefault();

    fetch(`http://localhost:8080/api/${currentTable}/update/${editId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${location.state.token}`,
      },
      body: JSON.stringify({
        [editName]: editContent,
      }),
    })
      .then((res) => res.json())
      .then(() => alert("Zaktualizowano!"))
      .catch((err) => alert("Internal server error. Try again later."));

    setTimeout(() => window.location.reload(), 300);
  }

  function addToTable(event) {
    event.preventDefault();

    for (let value of Object.values(dataObject)) {
      if (value === "") {
        alert("Wypełnij wszystkie pola!");
        return;
      }
    }

    fetch(`http://localhost:8080/api/${currentTable}/create`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${location.state.token}`,
      },
      body: JSON.stringify({
        ...dataObject,
      }),
    })
      .then((res) => res.json())
      .then(() => alert("Dodano!"))
      .catch((err) => alert("Internal server error. Try again later."));

    setTimeout(() => {
      setShowAdd(false);
    }, 0);
    setTimeout(() => window.location.reload(), 300);
  }

  React.useEffect(() => {
    if (tableData.length === 0) {
      return;
    }
    let newElements = Object.keys(tableData[0]).map((header, n) => {
      if (n === 0) return;
      setObject((prev) => ({ ...prev, [header]: "" }));

      return (
        <div className="flex flex-col gap-1 rounded-lg bg-slate-200 p-2 shadow-xl">
          <label htmlFor={header} className="font-bold">
            {header}
          </label>
          <input
            className="rounded-lg border-black p-1 shadow-lg"
            type="text"
            id={header}
            name={header}
            value={dataObject[header]}
            onChange={handleChange}
          />
        </div>
      );
    });
    setAddElements(newElements);
  }, [currentTable]);

  function handleChange(event) {
    const { name, value } = event.target;
    setObject((prev) => {
      return { ...prev, [name]: value };
    });
  }

  function safeDeleteMessage() {
    switch (currentTable) {
      case "atrakcje":
        return " Usunięcie atrakcji spowoduje usunięcie kolejki górskiej, która jest z nią powiązana.";
      case "kolejki":
        return " Usunięcie kolejki spowoduje usunięcie atrakcji, która jest z nią powiązana.";
      case "gastro":
        return " Usunięcie gastronomii spowoduje usunięcie atrakcji, która jest z nią powiązana.";
      case "klienci":
        return " Usunięcie klienta spowoduje usunięcie biletów, które są z nim powiązane.";
      case "typybiletow":
        return " Usunięcie typu biletu spowoduje usunięcie biletów, które są z nim powiązane.";
      case "parking/rodzaje":
        return " Usunięcie rodzaju miejsca parkingowego spowoduje usunięcie miejsc parkingowych, które są z nim powiązane.";
      case "stanowiska":
        return " Usunięcie stanowiska spowoduje usunięcie pracowników, którzy są z nim powiązani.";
      default:
        return "";
    }
  }

  return (
    <>
      <div className="relative">
        <Nav location={location.state} current="/adminview" />
      </div>
      {location.state?.role === "ADMIN" && (
        <main className="mt-24 flex p-6 pl-0 pr-2">
          <div className="-mt-6 flex h-14">
            {chooseView && <div className="mt-0 w-48"></div>}
            <div className="relative -mr-8 mt-0 w-14 rounded-br-lg bg-[#725AC1] shadow-xl">
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
                className={`linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75 ${
                          currentTable === "atrakcje" &&
                          "bg-slate-200 text-slate-800"
                        }`}
              >
                Atrakcje
              </button>
              <button
                className={`linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75 ${
                          currentTable === "kolejki" &&
                          "bg-slate-200 text-slate-800"
                        }`}
                onClick={() => changeTable("kolejki")}
              >
                Kolejki górskie
              </button>
              <button
                className={`linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75 ${
                          currentTable === "gastro" &&
                          "bg-slate-200 text-slate-800"
                        }`}
                onClick={() => changeTable("gastro")}
              >
                Gastronomie
              </button>
              <button
                className={`linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75 ${
                          currentTable === "pracownicy" &&
                          "bg-slate-200 text-slate-800"
                        }`}
                onClick={() => changeTable("pracownicy")}
              >
                Pracownicy
              </button>
              <button
                className={`linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75 ${
                          currentTable === "stanowiska" &&
                          "bg-slate-200 text-slate-800"
                        }`}
                onClick={() => changeTable("stanowiska")}
              >
                Stanowiska
              </button>
              <button
                className={`linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75 ${
                          currentTable === "klienci" &&
                          "bg-slate-200 text-slate-800"
                        }`}
                onClick={() => changeTable("klienci")}
              >
                Klienci
              </button>
              <button
                className={`linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75 ${
                          currentTable === "typybiletow" &&
                          "bg-slate-200 text-slate-800"
                        }`}
                onClick={() => changeTable("typybiletow")}
              >
                Typy biletów
              </button>
              <button
                className={`linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75 ${
                          currentTable === "bilety" &&
                          "bg-slate-200 text-slate-800"
                        }`}
                onClick={() => changeTable("bilety")}
              >
                Bilety
              </button>
              <button
                className={`linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75 ${
                          currentTable === "parking" &&
                          "bg-slate-200 text-slate-800"
                        }`}
                onClick={() => changeTable("parking")}
              >
                Miejsca parkingowe
              </button>
              <button
                className={`linear rounded-lg px-2 py-1 font-bold transition
                        duration-200 hover:bg-slate-200 hover:text-slate-800 active:opacity-75 ${
                          currentTable === "parking/rodzaje" &&
                          "bg-slate-200 text-slate-800"
                        }`}
                onClick={() => changeTable("parking/rodzaje")}
              >
                Rodzaje miejsc parkingowych
              </button>
            </div>
          )}
          <button
            onClick={() => setShowAdd(true)}
            className="absolute left-[calc(50vw-50px)] right-[calc(50vw-50px)] top-[114px] rounded-lg bg-slate-200 px-4 py-2 text-lg font-bold text-slate-800 transition duration-200 ease-linear hover:opacity-85 active:opacity-75"
          >
            Dodaj
          </button>
          <div className="flex w-full flex-col overflow-x-auto">
            {generateTable()}
          </div>
          {showEdit && (
            <>
              <div className=" fixed bottom-0 left-0 right-0 top-0 z-20 bg-black opacity-65" />
              <form
                onSubmit={(event) => updateTable(event)}
                className={`fixed left-[calc(50vw-439px)] right-[calc(50vw-439px)] z-20 mt-[12vh] flex flex-col gap-5 overflow-y-hidden rounded-xl bg-[#CAC4CE] p-8 text-center text-lg font-bold text-slate-800`}
              >
                <p>Edytuj pole:</p>
                <div className="flex flex-col gap-3 rounded-lg bg-slate-200 p-2 shadow-xl">
                  <label htmlFor="content" className="font-bold">
                    {editName}
                  </label>
                  <input
                    className="rounded-lg border-black p-1 shadow-lg"
                    type="text"
                    id="content"
                    name="content"
                    value={editContent}
                    onChange={(event) => setEditContent(event.target.value)}
                  />
                </div>
                <div className="flex flex-col gap-3">
                  <input
                    type="submit"
                    className="linear m-auto cursor-pointer rounded-lg bg-slate-200 px-2 py-1 font-bold text-slate-800
                        shadow-xl transition duration-200 hover:text-[#03C03C] hover:opacity-85 active:opacity-75"
                    value="Potwierdź zmianę"
                  />
                  <button
                    onClick={() => setShowEdit(false)}
                    className="linear m-auto w-[164px] rounded-lg bg-slate-200 bg-opacity-80 px-6 py-1 font-bold text-slate-800
                        shadow-xl transition duration-200 hover:text-[#FF033E] hover:opacity-85 active:opacity-75"
                  >
                    Anuluj
                  </button>
                </div>
              </form>
            </>
          )}
          {showAdd && (
            <>
              <div className="fixed inset-0 z-20 bg-black opacity-65" />
              <form
                onSubmit={(event) => addToTable(event)}
                className={`fixed bottom-[5vh] left-0 right-0 top-[5vh] z-20 flex items-center justify-center`}
              >
                <div
                  className={`flex max-h-[90vh] min-w-[30vw] flex-col gap-5 overflow-y-auto rounded-xl bg-[#CAC4CE] p-8 text-center font-bold text-slate-800`}
                >
                  <p>Wypełnij wszystkie pola:</p>
                  <div
                    style={{
                      display: "grid",
                      gridTemplateColumns:
                        addElements.length > 6 ? "1fr 1fr" : "1fr",
                      gap: "10px",
                    }}
                  >
                    {addElements}
                  </div>
                  <div className="flex flex-col gap-3">
                    <input
                      type="submit"
                      className="linear m-auto w-[100px] cursor-pointer rounded-lg bg-slate-200 px-2 py-1 font-bold text-slate-800
                        shadow-xl transition duration-200 hover:text-[#03C03C] hover:opacity-85 active:opacity-75"
                      value="Dodaj"
                    />
                    <button
                      onClick={() => setShowAdd(false)}
                      className="linear m-auto w-[100px] rounded-lg bg-slate-200 bg-opacity-80 px-6 py-1 font-bold text-slate-800
                        shadow-xl transition duration-200 hover:text-[#FF033E] hover:opacity-85 active:opacity-75"
                    >
                      Anuluj
                    </button>
                  </div>
                </div>
              </form>
            </>
          )}
          {showDelete && (
            <>
              <div className=" fixed bottom-0 left-0 right-0 top-0 z-20 bg-black opacity-65" />
              <div
                className={`fixed left-[calc(50vw-200px)] right-[calc(50vw-200px)] z-20 mt-[12vh] flex flex-col gap-5 overflow-y-hidden rounded-xl bg-[#CAC4CE] p-8 text-center text-lg font-bold text-slate-800`}
              >
                <p>
                  Czy napewno chcesz usunąć to pole?
                  {safeDeleteMessage()}
                </p>
                <div className="flex flex-col gap-3">
                  <button
                    onClick={deleteElement}
                    className="linear m-auto w-[100px] cursor-pointer rounded-lg bg-slate-200 px-2 py-1 font-bold text-slate-800
                        shadow-xl transition duration-200 hover:text-[#03C03C] hover:opacity-85 active:opacity-75"
                  >
                    Usuń
                  </button>
                  <button
                    onClick={() => setShowDelete(false)}
                    className="linear m-auto w-[100px] rounded-lg bg-slate-200 bg-opacity-80 px-6 py-1 font-bold text-slate-800
                        shadow-xl transition duration-200 hover:text-[#FF033E] hover:opacity-85 active:opacity-75"
                  >
                    Anuluj
                  </button>
                </div>
              </div>
            </>
          )}
        </main>
      )}
    </>
  );
}
