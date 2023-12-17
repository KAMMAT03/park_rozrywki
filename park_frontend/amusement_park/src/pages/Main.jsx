import React from "react";
import '../styles/Main.css';

export default function Main() {
    const elements = (
        <>
            <div className="main--kolejki--element">
                <img className="main--kolejki--img" src='coaster.jpg' alt="Roller coaster" />
                <p>Roller Coaster</p>
                <p>Super ekscytujaca kolejka zapewniajaca niezapomniane wrazenia dla calej rodziny!</p>
            </div>
            <div className="main--kolejki--element">
                <img className="main--kolejki--img" src='coaster.jpg' alt="Roller coaster" />
                <p>Roller Coaster</p>
                <p>Super ekscytujaca kolejka zapewniajaca niezapomniane wrazenia dla calej rodziny!</p>
            </div>
            <div className="main--kolejki--element">
                <img className="main--kolejki--img" src='coaster.jpg' alt="Roller coaster" />
                <p>Roller Coaster</p>
                <p>Super ekscytujaca kolejka zapewniajaca niezapomniane wrazenia dla calej rodziny!</p>
            </div>
            <div className="main--kolejki--element">
                <img className="main--kolejki--img" src='coaster.jpg' alt="Roller coaster" />
                <p>Roller Coaster</p>
                <p>Super ekscytujaca kolejka zapewniajaca niezapomniane wrazenia dla calej rodziny!</p>
            </div>
            <div className="main--kolejki--element">
                <img className="main--kolejki--img" src='coaster.jpg' alt="Roller coaster" />
                <p>Roller Coaster</p>
                <p>Super ekscytujaca kolejka zapewniajaca niezapomniane wrazenia dla calej rodziny!</p>
            </div>
            <div className="main--kolejki--element">
                <img className="main--kolejki--img" src='coaster.jpg' alt="Roller coaster" />
                <p>Roller Coaster</p>
                <p>Super ekscytujaca kolejka zapewniajaca niezapomniane wrazenia dla calej rodziny!</p>
            </div>
            <div className="main--kolejki--element">
                <img className="main--kolejki--img" src='coaster.jpg' alt="Roller coaster" />
                <p>Roller Coaster</p>
                <p>Super ekscytujaca kolejka zapewniajaca niezapomniane wrazenia dla calej rodziny!</p>
            </div>
            <div className="main--kolejki--element">
                <img className="main--kolejki--img" src='coaster.jpg' alt="Roller coaster" />
                <p>Roller Coaster</p>
                <p>Super ekscytujaca kolejka zapewniajaca niezapomniane wrazenia dla calej rodziny!</p>
            </div>
        </>
    )
    

    return (
        <>
            <nav className="main--nav">
                <p className="main--nav--parkname">Adrenaline City</p>
                <button>Bilety</button>
                <button>Parking</button>
                <button>Zaloguj</button>
            </nav>
            <main className="main--main">
                <h1 className="main--kolejki--title">Kolejki górskie:</h1>
                <div className="main--kolejki--grid">
                    {elements}
                </div>
            </main>
        </>
    )
}