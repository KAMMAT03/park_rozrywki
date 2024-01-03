import React from "react";
import Nav from "../components/Nav";

export default function Tables() {
    const [tableData, setTableData] = React.useState([]);

    const [tableType, setTableType] = React.useState("stanowiska");

    const [table, setTable] = React.useState();

    function generateTable(){
        if (tableData.length === 0){
            return (
                <div>
                    <p>Brak danych do wyświetlenia</p>
                </div>
            )
        }

        const headers = Object.keys(tableData[0]).map(header => {
            return <th  className=" bg-emerald-400 p-3 font-bold">{header}</th>
        });;

        const firstRow = (
            <tr>
                {headers}
            </tr>
        );

        const rows = tableData.map(row => {
            const values = Object.values(row).map(value => {
                return <td  className=" bg-emerald-300 p-3">{value}</td>
            });

            return (
                <tr>
                    {values}
                </tr>
            )
        });

        return (
            <table className=" w-full ml-60 border-collapse m-4 mr-4 text-left">
                <thead>
                    {firstRow}
                </thead>
                <tbody>
                    {rows}
                </tbody>
            </table>
        )
    }

    React.useEffect(() => {
        fetch(`http://localhost:8080/api/stanowiska/getall?pageSize=100`)
            .then((res) => res.json())
            .then((data) => setTableData(data.content));
        
        console.log(tableData);
    }, []);

    function changeTable(type){
        fetch(`http://localhost:8080/api/${type}/getall?pageSize=100`)
            .then((res) => res.json())
            .then((data) => setTableData(data.content));
    }

    return (
        <>
            <Nav />
            <main className=" mt-24 flex ">
                <div className=" fixed top-24 left-0 bottom-0 w-56 p-2 text-slate-200 gap-3
                 flex flex-col bg-emerald-700 font-bold">
                    <button>Zwiń</button>
                    <button
                        onClick={() => changeTable("atrakcje")}
                        className=" hover:bg-slate-200 hover:text-emerald-700 px-2 py-1 font-bold rounded-lg
                        transition duration-200 linear active:opacity-75"
                    >Atrakcje</button>
                    <button
                        className=" hover:bg-slate-200 hover:text-emerald-700 px-2 py-1 font-bold rounded-lg
                        transition duration-200 linear active:opacity-75"
                        onClick={() => changeTable("kolejki")}>Kolejki górskie</button>
                    <button
                        className=" hover:bg-slate-200 hover:text-emerald-700 px-2 py-1 font-bold rounded-lg
                        transition duration-200 linear active:opacity-75"
                        onClick={() => changeTable("gastro")}>Gastronomie</button>
                    <button
                        className=" hover:bg-slate-200 hover:text-emerald-700 px-2 py-1 font-bold rounded-lg
                        transition duration-200 linear active:opacity-75"
                        onClick={() => changeTable("pracownicy")}>Pracownicy</button>
                    <button
                        className=" hover:bg-slate-200 hover:text-emerald-700 px-2 py-1 font-bold rounded-lg
                        transition duration-200 linear active:opacity-75"
                        onClick={() => changeTable("stanowiska")}>Stanowiska</button>
                    <button
                        className=" hover:bg-slate-200 hover:text-emerald-700 px-2 py-1 font-bold rounded-lg
                        transition duration-200 linear active:opacity-75"
                        onClick={() => changeTable("klienci")}>Klienci</button>
                    <button
                        className=" hover:bg-slate-200 hover:text-emerald-700 px-2 py-1 font-bold rounded-lg
                        transition duration-200 linear active:opacity-75"
                        onClick={() => changeTable("typybiletow")}>Typy biletów</button>
                    <button
                        className=" hover:bg-slate-200 hover:text-emerald-700 px-2 py-1 font-bold rounded-lg
                        transition duration-200 linear active:opacity-75"
                        onClick={() => changeTable("bilety")}>Bilety</button>
                    <button
                        className=" hover:bg-slate-200 hover:text-emerald-700 px-2 py-1 font-bold rounded-lg
                        transition duration-200 linear active:opacity-75"
                        onClick={() => changeTable("parking")}>Miejsca parkingowe</button>
                    <button
                        className=" hover:bg-slate-200 hover:text-emerald-700 px-2 py-1 font-bold rounded-lg
                        transition duration-200 linear active:opacity-75"
                        onClick={() => changeTable("parking/rodzaje")}>Rodzaje miejsc parkingowych</button>
                </div>
                <div className="flex flex-col">
                    {generateTable()}
                    <button className=" bg-emerald-700 text-slate-200 rounded-sm p-1 m-auto">Dodaj</button>
                </div>
            </main>
        </>
    )
}