# Matrix-Vision
This project capture continuous real time image from system's default camera , encode it in Matrix like vision and then display it.
<br/>
Procedure used to implement this->
<br/>
1) I have used java OpenCv library to acquire image from System's default camera.<br/>
2) I applied contour detection algorithm in acquired image to find all contour pixels.<br/>
3) I highlighted all the contour pixels with dark green color with BGR value (0,255,0).<br/>
4) I used JPanel to represent a row of random characters. And multiplte Japanels(rows) are used to fill the screen with random characters.<br/>
5) For background matrix animation, I moved each panel row downwards to give a matrix rain simulation.<br/>
6) In order to highlight image from camera, I have set of value of background pixels to light green.


