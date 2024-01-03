import React from "react";
import Nav from "../components/Nav";

export default function Auth() {
    const [years, setYears] = React.useState([]);
    const [months, setMonths] = React.useState([]);
    const [days, setDays] = React.useState([]);

    const [registerView, setRegisterView] = React.useState(true);

    const [message, setMessage] = React.useState("");

    const [userData, setUserData] = React.useState({
        username: '',
        password: '',
        confpassword: '',
        imie: '',
        nazwisko: '',
        rok: '',
        miesiac: '',
        dzien: ''
    });

    function submitRegister(event){
        event.preventDefault();

        if (userData.password !== userData.confpassword){
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
                dataUrodzenia: userData.rok + '-' + formatMonth(userData.miesiac) + '-' + userData.dzien
            })
        }).then((response) => response.json())
        .then(json => {
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

    function formatMonth(month){
        switch(month){
            case 'Styczeń': return '01';
            case 'Luty': return '02';
            case 'Marzec': return '03';
            case 'Kwiecień': return '04';
            case 'Maj': return '05';
            case 'Czerwiec': return '06';
            case 'Lipiec': return '07';
            case 'Sierpień': return '08';
            case 'Wrzesień': return '09';
            case 'Październik': return '10';
            case 'Listopad': return '11';
            case 'Grudzień': return '12';
            default: return '01';
        }
    }

    function toggleRegistered(){
        setUserData({
            username: "",
            password: "",
            confpassword: "",
            imie: "",
            nazwisko: "",
            rok: "",
            miesiac: "",
            dzien: ""
        });
        setMessage("");
        setRegisterView(prev => !prev);
    }

    function handleChange(event){
        const {name, value} = event.target;

        setUserData(prev => ({
            ...prev,
            [name]: value
        }))
    }
    
    React.useEffect(() => {
        setYears(generateYears());
        
        const monthsSelect = ['Styczeń', 'Luty', 'Marzec','Kwiecień', 'Maj', 'Czerwiec',
        'Lipiec','Sierpień', 'Wrzesień', 'Październik', 'Listopad', 'Grudzień'];
        setMonths(() => {
            return monthsSelect.map(month => <option key={month} value={month}>{month}</option>);
        });
        
        setDays(generateDays());
    }, []);
    
    function generateYears(){
        const yearsSelect = [];
        for(let i = 2023; i > 1900; i--){
            yearsSelect.push(<option key={i} value={i}>{i}</option>);
        }
         
        return yearsSelect;
    }

    function generateDays(){
        const daysSelect = [];
        for(let i = 1; i < 32; i++){
            daysSelect.push(<option key={i} value={i}>{i}</option>);
        }
         
        return daysSelect;
    }

    return (
        <>
            <Nav />
            <form onSubmit={submitRegister} className="auth--form">
                <p style={{color: 'red'}}>{message}</p>
                <label htmlFor="username">Nazwa użytkownika:</label>
                <input
                    type="text" id="username"
                    name="username" value={userData.username}
                    onChange={handleChange}
                />

                <label htmlFor="password">Hasło:</label>
                <input
                    type="password" id="password"
                    name="password" value={userData.password}
                    onChange={handleChange}
                />

                {registerView && <>
                    <label htmlFor="confpassword">Potwierdź hasło:</label>
                    <input
                        type="password" id="confpassword"
                        name="confpassword" value={userData.confpassword}
                        onChange={handleChange}
                    />

                    <label htmlFor="imie">Imie:</label>
                    <input
                        type="text" id="imie"
                        name="imie"value={userData.imie}
                        onChange={handleChange}
                    />

                    <label htmlFor="nazwisko">Nazwisko:</label>
                    <input
                        type="text" id="nazwisko"
                        name="nazwisko" value={userData.nazwisko}
                        onChange={handleChange}
                    />

                    <label htmlFor="years">Data urodzenia:</label>
                    <div className="auth--date">
                        <select id="years" name="rok" value={userData.rok} onChange={handleChange}>
                            {years}
                        </select>

                        <select id="months" name="miesiac" value={userData.miesiac} onChange={handleChange}>
                            {months}
                        </select>

                        <select id="days" name="dzien" value={userData.dzien} onChange={handleChange}>
                            {days}
                        </select>
                    </div>
                </>}

                <input type="submit" value={registerView ? "Zarejestruj się" : "Zaloguj się"} />
            </form>

            <div className='login-toggle'>
                    <span>
                        {registerView ? 'Nie posiadasz jeszcze konta?' : 'Posiadasz już konto?'}
                    </span>
                    <button onClick={toggleRegistered} className='register-button'>
                        {registerView ? 'Zarejestruj się!' : 'Zaloguj się!'}
                    </button>
                </div>
        </>
    )
}