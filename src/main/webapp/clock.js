function onLoad() {
    var seconds = new Date().getSeconds();
    document.getElementById("seconds").innerHTML = (seconds < 10 ? '0' : '') + seconds;

    // Minutes
    var minutes = new Date().getMinutes();
    document.getElementById("minutes").innerHTML = (minutes < 10 ? '0' : '') + minutes;

    // Hours
    var hours = new Date().getHours();
    document.getElementById("hours").innerHTML = (hours < 10 ? '0' : '') + hours;
    setInterval(function() {
        // Seconds
        seconds = new Date().getSeconds();
        document.getElementById("seconds").innerHTML = (seconds < 10 ? '0' : '') + seconds;

        // Minutes
        minutes = new Date().getMinutes();
        document.getElementById("minutes").innerHTML = (minutes < 10 ? '0' : '') + minutes;

        // Hours
        hours = new Date().getHours();
        document.getElementById("hours").innerHTML = (hours < 10 ? '0' : '') + hours;
    }, 9000);
}