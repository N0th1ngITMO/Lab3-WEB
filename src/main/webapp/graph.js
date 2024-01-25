let r;
let x;
let y;
let dots = [];
const redColor = "#ff001e";
const greenColor = "#11ff00";
const cordsPattern = /(-?\d+\.\d+)(-?\d+\.\d+)/;
document.addEventListener('DOMContentLoaded', ()=>{
        drawNewGrafic();
    }
)

function setR(inputR){
    r = inputR;
}
function drawNewGrafic() {
    let elt = document.getElementById('calculator');
    let options = {
        keypad: false,
        settingsMenu: false,
        expressions: false,
        zoomButtons: false,
        expressionsTopbar: false,
        lockViewport: true,
        xAxisStep: 1,
        xAxisLabel: "X",
        yAxisLabel: "Y"
    };
    if (calculator != null) {
        destroyGrafic();
    }
    calculator = Desmos.GraphingCalculator(elt, options);
    if (r != null) {
        calculator.setExpression({
            id: "line1",
            latex: `y>=(-0.5)x-(${r}/2)\\left\\{-(${r}/2)<=y<=0\\right\\}\\left\\{-${r}<=x<=0\\right\\}`,
            color: Desmos.Colors.BLUE
        });
        calculator.setExpression({
            id: "line2",
            latex: `x>=-(${r}/2)\\left\\{0<=y<=${r}\\right\\}\\left\\{x<=0\\right\\}`,
            color: Desmos.Colors.BLUE
        });
        calculator.setExpression({
            id: "line3",
            latex: `y<=${r}\\left\\{y>=0\\right\\}\\left\\{-(${r}/2)<=x<=0\\right\\}`,
            color: Desmos.Colors.BLUE
        });
        calculator.setExpression({
            id: "line4",
            latex: `x^2+y^2<=(${r}/2)^2\\left\\{-3<=y<=0\\right\\}\\left\\{0<=x<=5\\right\\}`,
            color: Desmos.Colors.BLUE
        });
    }
}

function recountR(value){
    let rValue = value;
    console.log(rValue);
    dots.forEach((x) => {
        let nums = x.match(cordsPattern);
        if (rValue != null && nums != null) {
            drawXY(parseFloat(nums[1]), parseFloat(nums[2]), parseFloat(rValue))
        }
    })
}
function destroyGrafic(){
    calculator.destroy();
}

function getCoorOfClick(data){
    if(r != null){
        let graf = document.getElementById("calculator");
        let rect = graf.getBoundingClientRect();
        let X = data.clientX - rect.left;
        let Y = data.clientY - rect.top;
        // Note, pixelsToMath expects x and y to be referenced to the top left of
        // the calculator's parent container.
        let mathCoordinates = calculator.pixelsToMath({x: X, y: Y});
        x = mathCoordinates.x;
        y = mathCoordinates.y;
        console.log(x, y, r);
        rValue = document.getElementById("graphSelect:graph-r").value;
        document.getElementById("graphSelect:graph-x").value = x;
        document.getElementById("graphSelect:graph-y").value = y;
        if (document.querySelector("input[type=radio]:checked") !== null) {
            document.getElementById("graphSelect:graph-r").value = document.querySelector("input[type=radio]:checked")
                .value;
            addDots(mathCoordinates.x, mathCoordinates.y)
            drawXY(parseFloat(mathCoordinates.x), parseFloat(mathCoordinates.y), parseFloat(r))
        }
        updateBeanValues();
    }
}

function drawDot(){
    console.log(checkArea(x, y, r));
    if (checkArea(x, y, r)) {
        drawDots(x, y, greenColor)
    } else {
        drawDots(x, y, redColor)
    }
}
function drawXY(x, y, r) {
    if (checkArea(x, y, r)) {
        drawDots(x, y, greenColor)
    } else {
        drawDots(x, y, redColor)
    }
}
function addDots(x, y) {
    dots.push(x + '' + y);
}

function addDotsForForm(x, y) {
    dots.push(`${x}.0${y}.0`)
}

function drawDots(x, y, color) {
    calculator.setExpression({
        id: x + '' + y,
        color: color,
        latex: '\\left(' + x + ',' + y + '\\right)',
    })
}

function clear(){
    x = NaN;
    y = NaN;
    dots = []
}
