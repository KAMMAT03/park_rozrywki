import React from "react";
import Nav from "../components/Nav";

export default function Main() {
    const [kolejki, setKolejki] = React.useState([]);
    const [gastronomie, setGastronomie] = React.useState([]);
    const [atrakcje, setAtrakcje] = React.useState([]);

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

    function mapToDiv(element){
        return (
            <div className="main--kolejki--element">
                <img className="main--kolejki--img" src='coaster.jpg' alt={element.typAtrakcji} />
                <p>{element.nazwaAtrakcji}</p>
                <p>{element.opisAtrakcji}</p>
            </div>
        )
    }

    const kolejkiElements = kolejki.map(kolejka => {
        return mapToDiv(kolejka);
    });

    const gastronomieElements = gastronomie.map(gastronomia => {
        return mapToDiv(gastronomia);
    });

    const atrakcjeElements = atrakcje.map(atrakcja => {
        return mapToDiv(atrakcja);
    });
    

    return (
        <>
            <Nav />
            <main className="main--main">
                <h1 className="main--kolejki--title">Kolejki górskie:</h1>
                <div className="main--kolejki--grid">
                    {kolejkiElements}
                </div>
                <h1 className="main--kolejki--title">Gastronomie:</h1>
                <div className="main--kolejki--grid">
                    {gastronomieElements}
                </div>
                <h1 className="main--kolejki--title">Inne atrakcje:</h1>
                <div className="main--kolejki--grid">
                    {atrakcjeElements}
                </div>
            </main>
        </>
    )
}