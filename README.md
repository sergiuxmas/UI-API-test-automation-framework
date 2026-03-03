# UI-API-test-automation-framework
<h3>Written in Java, using Selenium, REST Assured, TestNG, Maven, Cucumber BDD</h3>
<p>Running tests from local machine is possible by clicking run.bat file , and after execution will be open up simple html report</p>
<p>Dependencies:
<li>Docker</li>
<li>Remote web driver</li>
</p>
<h3>Run remote web driver</h3>
<p>docker run -d -p 4444:4444 -p 5900:5900 --name selenium-chrome selenium/standalone-chrome:latest</p>
<h3>Restart remote web driver container</h3>
<p>docker restart selenium-chrome</p>