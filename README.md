<h1>CalculatorV2</h1>

<div style="text-align:center">
  <img width="10%">
<img src="img/defaultModeCalc.png" alt="Default Calc Image" width="22%"> </img>
  <img width="5%">
<img src="img/darkModeCalc.png" alt="Dark Calc Image" width="22%"> </img>
  <img width="5%">
<img src="img/lightModeCalc.png" alt="Light Calc Image" width="22%"> </img>
  <img width="10%">
</div>

<h2>Overview</h2>

CalculatorV2 is a JavaFX-based calculator application that supports basic and scientific calculations. The project is meant to offer a clean, responsive user interface with themes and two seperate layouts.

<h3>Features</h3>

<b>Basic Layout:</b> Perform standard arithmetic operations.

<b>Scientific Layout:</b> Includes advanced functions such as logarithmic, and exponential calculations. (Plan to add trigonometric functions)

<b>Themes:</b> Choose between different themes to customize the appearance.

<b>Persistent:</b> Your chosen theme and layout will save and load automatically.

<b>Keyboard Input Support:</b> <br>
shift + 5 -> % <br>
shift + - -> Negative <br>
N -> Natural Logarithm <br>
L -> Logarithm <br>
X -> Exponent <br>
Esc -> Clear <br>
Enter -> Equals

<b>Reccomended Fonts:</b> (Uses system font if not installed) <br>
- <a href="https://www.dafont.com/ds-digital.font">DS-Digital</a> (Included in repo, Original Author: Dusit Supasawat)
- Adobe Source Sans Pro (Light/Semi-Bold)

<h2>Steps to run:</h2>

<h3>Clone the repository:</h3>

<code>git clone https://github.com/jcjr30/Calculator.git</code>

<h3>Navigate to the project directory:</h3> 

<code>cd Calculator</code>

<h3>To run the application, use the following Maven command:</h3>

<code>mvn javafx:run </code>

<br>

<h3>To create a portable version, use the following Maven command:</h3>

<code>mvn javafx:jlink</code>

<h3>Then run the generated .bat file (target/app/bin/app.bat)</h3>


<br>
This project is licensed under the GNU GENERAL PUBLIC LICENSE Version 3.

