
const url = 'https://www.cbr-xml-daily.ru/daily_json.js'
const  isAsinc = false;
function main() {
    const request = new XMLHttpRequest();
    request.open('GET', url, isAsinc);
    request.send();
    const data = JSON.parse(request.response);
    console.log(data)
}

function main2() {
    $.ajax(url).done(date => console.log(JSON.parse(date)));
}

function main3() {
    const request = fetch(url);
    request
        .then(response => response.json())
        .then(data => console.log(data));
}

function main5() {
    fetch(url)
        .then(response => response.json())
        .then(data => console.log(data));
}

async function main6() {
    const response = await fetch(url);
    const data = await response.json();
    console.log(data);
}

async function main7() {
    const response = await fetch(url);
    const data = await response.json();

    let valutes = data.Valute;
    console.log(valutes);
    console.log(valutes.AMD.Name);
}

main7();