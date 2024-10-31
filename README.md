<h1>CalculatorV2</h1>

<div class="images--container" style="text-align:center">
<img class="images" src="img/defaultModeCalc.png" alt="Default Calc Image" height="450" style="border: 10px solid black"> </img>
<img class="images" src="img/darkModeCalc.png" alt="Dark Calc Image" height="450"> </img>
<img class="images" src="img/lightModeCalc.png" alt="Light Calc Image" height="450"> </img>
</div>

<h2>Overview</h2>

CalculatorV2 is a JavaFX-based calculator application that supports basic and scientific calculations. The project is structured to offer a clean, responsive user interface with themes and two seperate layouts.

<h3>Features</h3>

<b>Basic Layout:</b> Perform standard arithmetic operations.

<b>Scientific Layout:</b> Includes advanced functions such as logarithmic, and exponential calculations. (Plan to add trigonometric functions)

<b>Themes:</b> Choose between different themes to customize the appearance.

<b>Keyboard Input Support:</b> <br>
shift + 5 -> % <br>
shift + - -> Negative <br>
N -> Natural Logarithm <br>
L -> Logarithm <br>
X -> Exponent <br>
Esc -> Clear <br>
Enter -> Equals

Uses Maven for building the project.

<h2>Steps:</h2>

<h3>Clone the repository:</h3>

<code>git clone https://github.com/jcjr30/Calculator.git</code>

<h3>Navigate to the project directory:</h3> 

<code>cd Calculator</code>

<h3>Build the project using Maven:</h3> 

<code>mvn clean package</code>

<h3>To run the application, use the following Maven command:</h3>

<code>mvn javafx:run </code>

or run the generated .exe file

<br>
This project is licensed under the GNU GENERAL PUBLIC LICENSE Version 3.
<style>
.images--container {
display:flex;
gap:10px;
}
.images {
border: 10px solid black;
padding-top:-40px;
}
</style>
