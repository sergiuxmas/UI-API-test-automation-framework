# UI-API-test-automation-framework
<h3>Written in Java, using Selenium, REST Assured, TestNG, Maven, Cucumber BDD</h3>
<p>Running tests from local machine is possible by clicking run.bat file , and after execution will be open up simple html report</p>
<p>Dependencies:
<li>Docker</li>
<li>Remote web driver</li>
</p>
<h3>Run remote web driver that includes noVNC</h3>
<p>docker run -d -p 4444:4444 -p 7900:7900 --name selenium-chrome selenium/standalone-chrome:latest</p>
<p>You can watch the browser at: http://localhost:7900</p>
<p>Password: secret</p>
<h3>Remote driver status API</h3>
<p>http://localhost:4444/status</p>

<h3>Restart remote web driver container</h3>
<p>docker restart selenium-chrome</p>

<h3>Run</h3>
<li>Selenium: mvn test -Pselenium</li>
<li>Playwright: mvn test -Pplaywright</li>
<li>VM option: -Dbrowser.engine=playwright or -Dbrowser.engine=selenium</li>
