import React from "react";
import Nav from "../components/Nav";

export default function Auth() {
  const [years, setYears] = React.useState([]);
  const [months, setMonths] = React.useState([]);
  const [days, setDays] = React.useState([]);

  const [registerView, setRegisterView] = React.useState(true);

  const [message, setMessage] = React.useState("");

  const [userData, setUserData] = React.useState({
    username: "",
    password: "",
    confpassword: "",
    imie: "",
    nazwisko: "",
    rok: "",
    miesiac: "",
    dzien: "",
  });

  function submitRegister(event) {
    event.preventDefault();

    if (userData.password !== userData.confpassword) {
      setMessage("Passwords do not match!");
      return;
    }

    fetch("http://localhost:8080/api/auth/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: userData.username,
        password: userData.password,
        imie: userData.imie,
        nazwisko: userData.nazwisko,
        dataUrodzenia:
          userData.rok +
          "-" +
          formatMonth(userData.miesiac) +
          "-" +
          userData.dzien,
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

  function formatMonth(month) {
    switch (month) {
      case "Styczeń":
        return "01";
      case "Luty":
        return "02";
      case "Marzec":
        return "03";
      case "Kwiecień":
        return "04";
      case "Maj":
        return "05";
      case "Czerwiec":
        return "06";
      case "Lipiec":
        return "07";
      case "Sierpień":
        return "08";
      case "Wrzesień":
        return "09";
      case "Październik":
        return "10";
      case "Listopad":
        return "11";
      case "Grudzień":
        return "12";
      default:
        return "01";
    }
  }

  function toggleRegistered() {
    setUserData({
      username: "",
      password: "",
      confpassword: "",
      imie: "",
      nazwisko: "",
      rok: "",
      miesiac: "",
      dzien: "",
    });
    setMessage("");
    setRegisterView((prev) => !prev);
  }

  function handleChange(event) {
    const { name, value } = event.target;

    setUserData((prev) => ({
      ...prev,
      [name]: value,
    }));
  }

  React.useEffect(() => {
    setYears(generateYears());

    const monthsSelect = [
      "Styczeń",
      "Luty",
      "Marzec",
      "Kwiecień",
      "Maj",
      "Czerwiec",
      "Lipiec",
      "Sierpień",
      "Wrzesień",
      "Październik",
      "Listopad",
      "Grudzień",
    ];
    setMonths(() => {
      return monthsSelect.map((month) => (
        <option key={month} value={month}>
          {month}
        </option>
      ));
    });

    setDays(generateDays());
  }, []);

  function generateYears() {
    const yearsSelect = [];
    for (let i = 2023; i > 1900; i--) {
      yearsSelect.push(
        <option key={i} value={i}>
          {i}
        </option>,
      );
    }

    return yearsSelect;
  }

  function generateDays() {
    const daysSelect = [];
    for (let i = 1; i < 32; i++) {
      daysSelect.push(
        <option key={i} value={i}>
          {i}
        </option>,
      );
    }

    return daysSelect;
  }

  return (
    <>
      <Nav />
      <div className="mt-24 flex flex-col items-center justify-center p-4">
        <div
          className="flex min-w-[40vw] flex-col justify-center gap-2 rounded-xl p-6"
          id="auth"
        >
          <form onSubmit={submitRegister} className="flex flex-col gap-2">
            <p className="text-red-600">{message}</p>
            <div className="flex flex-col rounded-lg bg-slate-200 p-2 shadow-xl">
              <label htmlFor="username" className="select-text font-bold">
                Nazwa użytkownika:
              </label>
              <input
                className="rounded-lg border-black p-1 shadow-lg selection:border-2"
                type="text"
                id="username"
                name="username"
                value={userData.username}
                onChange={handleChange}
              />
            </div>

            <div className="flex flex-col rounded-lg bg-slate-200 p-2 shadow-xl">
              <label htmlFor="password" className="font-bold">
                Hasło:
              </label>
              <input
                className="rounded-lg border-black p-1 shadow-lg selection:border-2"
                type="password"
                id="password"
                name="password"
                value={userData.password}
                onChange={handleChange}
              />
            </div>

            {registerView && (
              <>
                <div className="flex flex-col rounded-lg bg-slate-200 p-2 shadow-xl">
                  <label htmlFor="confpassword" className="font-bold">
                    Potwierdź hasło:
                  </label>
                  <input
                    className="rounded-lg border-black p-1 shadow-lg selection:border-2"
                    type="password"
                    id="confpassword"
                    name="confpassword"
                    value={userData.confpassword}
                    onChange={handleChange}
                  />
                </div>

                <div className="flex flex-col rounded-lg bg-slate-200 p-2 shadow-xl">
                  <label htmlFor="imie" className="font-bold">
                    Imie:
                  </label>
                  <input
                    className="rounded-lg border-black p-1 shadow-lg selection:border-2"
                    type="text"
                    id="imie"
                    name="imie"
                    value={userData.imie}
                    onChange={handleChange}
                  />
                </div>

                <div className="flex flex-col rounded-lg bg-slate-200 p-2 shadow-xl">
                  <label htmlFor="nazwisko" className="font-bold">
                    Nazwisko:
                  </label>
                  <input
                    className="rounded-lg border-black p-1 shadow-lg selection:border-2"
                    type="text"
                    id="nazwisko"
                    name="nazwisko"
                    value={userData.nazwisko}
                    onChange={handleChange}
                  />
                </div>

                <div className="flex flex-col rounded-lg bg-slate-200 p-2 shadow-xl">
                  <label htmlFor="years" className="font-bold">
                    Data urodzenia:
                  </label>
                  <div className="flex justify-around">
                    <select
                      className="rounded-lg p-1 shadow-lg"
                      id="years"
                      name="rok"
                      value={userData.rok}
                      onChange={handleChange}
                    >
                      {years}
                    </select>

                    <select
                      className="rounded-lg p-1 shadow-lg"
                      id="months"
                      name="miesiac"
                      value={userData.miesiac}
                      onChange={handleChange}
                    >
                      {months}
                    </select>

                    <select
                      className="rounded-lg p-1 shadow-lg"
                      id="days"
                      name="dzien"
                      value={userData.dzien}
                      onChange={handleChange}
                    >
                      {days}
                    </select>
                  </div>
                </div>
              </>
            )}

            <input
              type="submit"
              value={registerView ? "Zarejestruj się" : "Zaloguj się"}
              className="linear cursor-pointer rounded-lg bg-slate-200 px-6 py-2 text-xl font-bold shadow-lg transition duration-200 hover:opacity-90 active:opacity-75"
            />
          </form>

          <div className="flex gap-3 self-center text-slate-200">
            <span>
              {registerView
                ? "Nie posiadasz jeszcze konta?"
                : "Posiadasz już konto?"}
            </span>
            <button
              onClick={toggleRegistered}
              className="rounded-lg px-2 font-semibold transition duration-200 ease-in hover:bg-slate-200 hover:text-black active:opacity-90"
            >
              {registerView ? "Zarejestruj się!" : "Zaloguj się!"}
            </button>
          </div>
        </div>
      </div>
      <img
        className="fixed bottom-0 left-0 -z-10 opacity-30"
        src="bg-image.jpg"
        alt="background image"
      />
    </>
  );
}
