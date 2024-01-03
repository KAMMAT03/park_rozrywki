import React from "react";
import Nav from "../components/Nav";

export default function Bilety() {
    const [typyBiletow, setTypyBiletow] = React.useState([]);

    React.useEffect(() => {
        fetch("http://localhost:8080/api/typybiletow/getall")
            .then((res) => res.json())
            .then((data) => setTypyBiletow(data.content));
    }, []);

    const typyElements = typyBiletow.map(typ => {
        return (
            <div className="bilety--bilet">
                <div className="bilety--bilet--info">
                    <p className="bilety--standard">{typ.standard}</p>
                    <p className="bilety--ulga">{typ.ulga}</p>
                    <p className="bilety--opis">{typ.opisTypuBiletu}</p>
                </div>
                <div className="bilety--cena">
                    <p>{typ.cena} zł</p>
                    <button>KUP</button>
                </div>
            </div>
        )
    })

    return (
        <>
            <Nav />
            <main className="bilety--main">
                <h1>BILETY</h1>
                <div className="bilety--elements">
                    {typyElements}
                </div>
            </main>
        </>
    )
}