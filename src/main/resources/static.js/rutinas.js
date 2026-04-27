console.log("World Buddy cargado correctamente.");


function mostrarClave(id) {
    const input = document.getElementById(id);

    if (input.type === "password") {
        input.type = "text";
    } else {
        input.type = "password";
    }
}

function convertirAMPM(horas, minutos, segundos) {
    let periodo = horas >= 12 ? "PM" : "AM";
    let hora12 = horas % 12;

    if (hora12 === 0) {
        hora12 = 12;
    }

    return hora12.toString().padStart(2, "0") + ":" +
           minutos.toString().padStart(2, "0") + ":" +
           segundos.toString().padStart(2, "0") + " " + periodo;
}

function obtenerHoraValida(horaTexto) {
    if (!horaTexto) return null;

    horaTexto = horaTexto.trim();

    let esPM = horaTexto.includes("PM");
    let esAM = horaTexto.includes("AM");

    horaTexto = horaTexto.replace("AM", "").replace("PM", "").trim();

    let partes = horaTexto.split(":");

    if (partes.length < 2) return null;

    let horas = parseInt(partes[0]);
    let minutos = parseInt(partes[1]);

    if (isNaN(horas) || isNaN(minutos)) return null;

    if (esPM && horas < 12) horas += 12;
    if (esAM && horas === 12) horas = 0;

    return { horas, minutos };
}

function actualizarRelojes() {
    const relojes = document.querySelectorAll(".reloj-ciudad");

    relojes.forEach(function (reloj) {
        let horaBase = reloj.getAttribute("data-hora");
        let hora = obtenerHoraValida(horaBase);

        if (hora === null) {
            reloj.textContent = "Hora inválida";
            return;
        }

        if (!reloj.dataset.inicio) {
            let fechaBase = new Date();
            fechaBase.setHours(hora.horas);
            fechaBase.setMinutes(hora.minutos);
            fechaBase.setSeconds(new Date().getSeconds());

            reloj.dataset.inicio = fechaBase.getTime();
            reloj.dataset.carga = new Date().getTime();
        }

        let tiempoInicial = parseInt(reloj.dataset.inicio);
        let tiempoCarga = parseInt(reloj.dataset.carga);
        let tiempoActual = new Date().getTime();

        let diferencia = tiempoActual - tiempoCarga;
        let nuevaFecha = new Date(tiempoInicial + diferencia);

        reloj.textContent = convertirAMPM(
            nuevaFecha.getHours(),
            nuevaFecha.getMinutes(),
            nuevaFecha.getSeconds()
        );
    });

    const indicadores = document.querySelectorAll(".dia-noche");

    indicadores.forEach(function (indicador) {
        let horaBase = indicador.getAttribute("data-hora");
        let hora = obtenerHoraValida(horaBase);

        if (hora === null) {
            indicador.textContent = "Hora inválida";
            return;
        }

        indicador.textContent = (hora.horas >= 6 && hora.horas < 18) ? "Día" : "Noche";
    });
}

setInterval(actualizarRelojes, 1000);
actualizarRelojes();