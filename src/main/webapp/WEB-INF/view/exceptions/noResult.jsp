<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <title>No result</title>

</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container" style="margin-left: 600px">
    <div class="row text-center">
        <div class="col-sm-6 col-sm-offset-3">
            <br><br>
            <h2 style="color: #0d0d0d; margin-top: 40px">No result for request!</h2>
            <img style="margin-top: 10px" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOAAAADgCAMAAAAt85rTAAABfVBMVEU3q5j+AAABAQE0NSWe3eTuuotaVFT/yJmW0NT////8AAD8///6AAA3q5X3AAA2NybxAADrAADuuY81rZRaVVI3rpo3NCXmAABkSCE3MycGAAA7spqWz9c9rZknqZY1jHtVT08hIRoqKiEvpo4ipozk9fLnMS9Qr5yDxbny2dn25ubvx8fqp6fpnZqfz8YsbV4sXFE7ootFQUE4MzMqJycVFhETExDY7evF5uC42tV0wbNduKiazMT38vHrsrPtzMvmKCHlPTzjSEjoWlftdHXqgX/pjYjmfHzoFRXorKrmZWLnJyTrVVTuoJ/ihIPqfHjcSUDtSU7y19sdNC83fW59q5igjnqqopO+tqhEhGhgOgAUKCBPd1tbTCYhST9XZEZUbExaSSM/j3KKr5CptJiElHRsRx9YOw1xrpK+j2uMfFnZvJTMu5GdspO6tpQLGBPwy55ou7cwQUNaeHddZWV8naSJs7riza/J1MVKgF6D0M0cPTciTUAqZFg7mYh0i/zCAAARRklEQVR4nO1djV/aSBoOdGUdJpMNhlQrEmmDIuAXtsUPGq3fitVeV+9uu966d7VXvbOr9rrt3dnWv/3emSQQIEhAJOF+eda6am2dh3fmed73ncmU43z48OHDhw8fPnz48OHDhw8fPnz48OHDhw8f9SFXfCI3+o6uA5EURVXpGwPhCGFfLr3rboIyJ6VG8MjI7Ozc/OTk2NhCIcVRrmqSsk1KCsRU7l6KMPbkAkbVwHhkdq5EmKhK1zKUOfUZQtpidmkpndE0g10N4Weq2wNtGeo8QplRnhcEMSJGIlvPl1dW19Y3Nsa3F7PpdMYgjVNuj7M1yEp8FuFshOcDgXA4wN6F9Q+AsSBSRLa29jAa6cIQyhxJFjDC42KgAcRFjOZUUFu3h9wcYPmNYYw3xTB/M79wWExj9CxJiNtDbhJJWH7aCyHciGAgHHiuYVzoNn5kjspLQ3Y6xVVYhvGumaJgfkRNjSC8FNElpTFBYQKj2a4RGvBs6u4gLw75gaiKWYQm1e5wewnkE9wdrwvO2DGG4UgGowWlCxjC/JTUOYS1VYFvgiAfGIUUpyv8XlY4WH4gLwFH+mJGMCCs0WXofSmVVXB3lO13Jp9WjsI4RvNJt8ffALIM7o7QNrh70wTD4hJGY4rbFG4CSIQyD0tpr2F2Zs9wS8Mo5eXKCZKtWSYvrfCjQrMChaKXQyildHlpenoaEeSFPYTmkvZ9G/chSylw96X+lsJnQFj0cvVLxhDedpqd2QeRj6QRLnjVLJQxlIkEbsGP/tktDY14tXBKTqJ0S/ppZShAYTHnUTeEAnCpNQEtE+R5WlhMenIZkuQcWrwlQSh+w7SwWFA4701TkpxF27clSClGMginvLgOkyNooyUHrCTIh5c1NOtFv09itH5rfkxoxhGKE++5PUGo2AaCsAoXEVY9SDCF0Eo7+PHikjc7wQsIjRp964AAYgFvPOQ1fNOpqZjxYulLpDGEtnSCgZ3Fic3iyk4EFIMPC80SjGhQ+XqPYPIZ0iIGwReY7iLhRQjfxO5e8cVohFmcmcUZ3xUI2CeukK5NejCC6iSkosZ419DG6ubu4ksQjAzbMdPWeBbMnWLxVYRn/QxqmfbTdxlBZe85jaGJTFo0xruHVyIRUYDhR1Bm9eXEtoa3IJpbWbpZltmkDUVh+Xm/KNq1FsOrkMp4z+iJOgupqDHeCZTRtMwyTMF+nI2IoriBViFFSaP0xjqUfDQf6MdIy/yY3RNZeiYAzE22wEuEvJjJKCNoUQ9gWFjEmaWlzA6dbjiTzqSzEMFAYIN1o4RRDY+CDqHMUkaDdSrQdhN8y/ZE0diJmkDYe/RgimI0bgQwvKT1CwIrnWC6ZdKwBl8KASGLd5hp7KH1cGAFTQii2J8G5ryYwRrd2s6K9C/gtxFOeq/1RJIw9YxVBAOe2FtbGRXD4TW8J0YW8R9oIZTW+oEfHy6ijXCgiMbX19dWMxoIbD/6sT+y8yqLX9A/LWTRrAcrQhJHeNMkSHfewSlWw+E9mr6N4h+3RIgMLlIphUxsNcCvMyOBqMO67UfZnZXR/nHMUj0hDSWv5+aoTAqlVBTkJDJa3JwYHw3zu2iZ9pIwXocqAdwiIvZP4Awstgm0+2oVa0VYgoEd/eQFptFkicy84j2RUSBTW6kVfbGf+l1kN10EeVkDHlDsaaMBXlhEz+lqK1IveYHTG3saXnzObBESmWeK91qHCmRqO7WexnKVcABMgK7C0XGQ1AmawQlLuD8QeI5xFhR1Da/xwgbaFJhNjDKf9x7BSYQitQR5Xk+/adYNJsCDtwv0U2FzFxyQ38PoVSCsu6Sm7S7Tb15BqOBFgvNIqyXYCMLWCmQ/L9O0Hf4Soz1KsOjNg0HqHMo03TSkYaWngkTqHsLy3jL94p4nfZ6DTC3ddMtJJwhh4/W1yqbzLhrxoA3STC17+5YTy/O86fMqRou36NpbCC6heQ82LAhBkIreZufFBPV5LxJUR5C21RaCGhrz5KGnOC5X9LfBlkY79x6EUsBoSWS6eCssg897MoJccgzR8vW2BFcRjnvRBzm2f4YnhFsuw/Aawt4MIBWaOYTXhFvsMNFqcRuNqN5lOGJbMzknGBa9fbBSAinVdvSmbiv02PltXPBeU7QEtYBwJtKi0IT5SBahkZT3utolyFRK0VKkxRBG0vRosydNsAQ5OcnO+jZLkQZ9K428vP5MJEFKN5o5Davz4/nRDKLPT3gfIKV4rdk5GhZWNITmk96VFwtASvEK34ThQ/iEVYTQpAdbMXagLVIwi2YIso7imEK817G3BZXSdBMdqLBA+9wLanfEjwKkFGUdHsyDGlLcAHsodIO+GCCcMqebhaPwiePU3r2af9qBEEmdRXhPaFjh02+IQHo222UP18nAEcyi2FBKw3wgsoQ7YH/t38yhBT5ebvjwIKQvmDaZ7jr9JIA2SzQt8LV+1tI1u21VM5a2N55ndPu78/xavpbafTqMZqWZftFyuiBQQ3BZw3hMvftz9lJuP3ggtzeEkkRbGNpSdnF8YmOz+OIVOzciWrPU8Avg1xH7k4aDweB0XGrzSwlSaoWmaZl0Nru9O7FeXH21vBMpUvvrSHUkHQDB4GGuzUEk8bFnk/NzsyNsN758EQK7LABqf9wx+yOMYHDwINZWgrKkqkmV/uJSqcIf//Tnn37S2ZpUZzlJ7shufGwqqGOayO1fEewyDiK9/vnk559zSWAcTxUWaGzn1U5l15JJEKZpTLqbnym/fjcwMEBYygKmRO9gSXJSh9ovJBcs4SB2Nz9D/gvw+yXOUTeirsvedei0iCzHjsoMp+Nt1hrjZ/yVEqwKWYcKJFh3w2WCwf3cHQSRSL/CFP3VtZIvdmFhCGra/rkT+3Vg4N1fXStp5amgFdPtP9Uv/wIE/+JezW7ITP6pMU2v2zyZCPmbuwRNmYk+MYI4HGvr88JEBY1597ozvm4HU2YeR2f0dUjVtI2DITkgePLGPYKcZMhMNKoHcbCtairLOerz7S/KnI/ATNdmQqHQY0NNYZq2qVkiS2+A30DOvTUoS4bMPAGCoUS+bPq3+2v1RJQo0puTgYG/0fnp0hyVJVNmKMGQqTX7t3zNWU6mKOT47d/fnZz8InF30f9xCFNmZhjD0Ixp+rerL2gL8fT8rKcP8I9/phRK0aUWISGHuhXqBEOJh6bpt/SS6y+LRFJvz3oY+uivs/NjFfJRVzI2YspMwmBoas1US2IKL4ui5I7f9/X0lAiyj8+OVZncRT7fELIhM49NglFda/KtEeSU03OYlz3V6Ot7f6q6QpCTrTJDCRqWONzSolF/s6NHCfb0vXXnKIxUKTMlrZluesnA/FTP7diZk/Uy5UZOQ8hF2QpLUYT0u+nDY+B7Zz11CVKcEjfsQq6WGcowRNsYTQ5GOe27mV9PvFPtmAqQaplheBL80tSKIZJybLv6LHP0rGP9pkoY2Uw+ag0hLMOcwz9P1yohybc3Bo/iXHEnn5FtZCYaHQxOOVQZqkZEPe+5OYAQwpw7KalM4rUyQw3/0JlvUX5K7uxmdn0958end06l7hCnKq1QT9r0+t4BqLuf9TTQlz63FiCFmc1Y52go+jA47YAgDBrkhdJrMEFhBd49k3ooyYxFZ0Bm8g5ecqiN1beNuOkxPHbrSCiY73CtFYZCNF2rS5GOFWoqWaLy4gzv3QzhhY0VPg5+qbuzJtOcRIHwKamzxvLJ0NPn3m0qcj2ZqVvaK6oaL3y46r2C6WmfXtcQ/P7Mzdtg7WQmlLexQroHxhU+fOjt7R2Ct6FLPToN8D2FmypjFk2VVjgDVqg32OgdxfChyqUKC1e9Fgz9p17UKrhRXJ66eYGhmc1UGEVoMHjN+sAQNqD2gVIbGhqyEux9doM9fG/B2SlRiJsnC43ejFVmaOU7LZnUKLNKbjrqpDDfV+FSlSRXCRob2vkyvcRMIvGvjwtXV7a8SvipJKLmWqzmpsO9RI0CXtrcoMUKo9HfP376PDT0Xe/QjewoboqbBcduPxgRq5AZRmyIoRHBs8bkPEFQsspM4nNjYgaGfmrIja1Bl+kB5IuyFSY+OqTX2/vdvAN64BGuH341d5oe6iFsGEBYoAYuG9I7P/XAzZPELJqYzEQ/3czwOwv+3ZDgsRceTQKX+lq2wujvjrgx/KdxBL1AkDPPzejNp8RnJ9wYGi9C1yrBKkiHZZmJVsuMPTknBC9/c6mbVgNDZp7oicxQyeTrc3OiMm9znrnLyNzQ1nX0E/P6RuQaEXwv0Us4vDFDOWJkM7oV/u6E280E+/7x7rWXLnkvyYwxR29F8PL98cDJycmbmFfCR0GM3kyCEfzUOsHL81NVyZ3QU0Buc6qE0Zt5TOuJUNQhv/nL9+fHx6enqVTq9Pj4t/P3b0+TigQrGgi+u/ZQ/LhyC1ifo5+dEbyi/04kB/+VAHkZkSR6zOnE6Q5OZyCT2NeSzEQTH50R/FDdDtT/JUzpDSPorQiavZknzciMrYrIMiM44LkLAOJlmQk5k5mCbT8XzIGduHe9SqqCeW7GyLgd8LuytwFCYv/9mZ7j8hZDYhZNeacyc1WnXU1gDb5+k8t5bYZy5sMGzAqjDWXmQ/1CiEgeymEsqJSZmxPtK3eOTdwSRtEUaigzVwVF9tYKcwYjm9H3YerLzFXKE2V6K9Bl5qnRP6wTPE7p2DM6bYeRzSSirH9YSw7Ysaebu5QeyJ8uM49ZbyZqR05H1xI0dpoGK62w9/NCgVO77PICe5gtYEthP/Tp40wwZwSvW5deCbJxPM/MuIc+/Z5IhKKDU8ZDMV1PsLTTZOykJaJRWv4+vrijx0Q7D2LKTMg8GEQJJoIeq85bh0T0naZ8qOKEZX662SOyXgXUFFNlKywRnAn+vxCkFldzJAEQPOjK3NMWskVmSnj85a6et+88jA3typNPifpHu7oOkt4Crjz5BFbYxh/hsp0a2Yz1gGUiH9yPWUZWO0DzN2wRq4JE2ntLSJMwspnyI01R9tTWtUwkGFx52ISQOKhunCEVz+Vy19fwNqzjQMcU4Nu3acDXr1+Pjr4w7O9P52LuhdHYacpbwkcxLceu2TiP9HHu7x8eXuTz+UH9GFGz+NruB9ubgFwhM8ZDd8F8fKr+cG0A3PNPDTy04tEj/UnFo2s2UV2gSeKHusxAImo+3Qtgw3qqD7EC9x48uMfwgwX0k4ov3rNCv5/gy3DMHfcxZMZcfRY81cd3//59YHDvh/sUwOFe03gUpE+2B/eHXZEbowU8E81X8Qs+MAnq/39Acb95ehTGE7X7w51fi8R4QruGXvBRi2Ts8MMj4+8/POj0kXXQ/uEabmyCthotW9x/YASRUexoMk+42KEdwbbyYxSfmpI71dkoli6CqJyg7aV3j6rTo2CJYmcNI1fL7+H9NgdQhzlPg4Pfch2sOq3XWhkvcTOjBm01X477BszPqDPSj0rm+KgsZtN3cb2ULeRamXE+QWHgOiXGARzTJAiEHrCkQE8QzNTmqeWnTHdonsqyVCUzD6soWD8rBYR++cG9UopjEgDkaz3HBoOOnutrD6RKmckzGszX6btHVTEAAo4YNMRwx5SGVMpMvl0MGqCDdmicAu4sjjrYYZZzF40H1FYMfh3uZD5DYp1leDiVk+WObmERrnMMIXj6HZKd7S+T5mr40mgdfttg8OLi8HD/y5cjCJ4797HYJGwOkb+42KdDP6L9pm/fvk1NHRwc0GbUNSCXy6mExOKlNptb3RkS+1I78qeHMPKjo6OvbOD6yA+MkV/T803GuGW9BWdpw5lNRc5oxBDjyi7i0qVPdBx6E5COnKPNwVhFl5OOnn5FkqSYREGIZNwFS7hyN8nJ4F0iKJU7uToB80OdhBkPy1043bYFXDXeuleudRsvHz58+PDhw4cPHz58+PDxf4D/AfzCnZRs9+B4AAAAAElFTkSuQmCC" height="300px"
                 width="280px">

            <h5 style="margin-top: 30px">All exceptional situations were predicted and fixed.</h5>
            <h5 style="margin-top: 10px">So, if you see this message:</h5>

            <p style="font-size:20px; color: #0d0d0d; margin-top: 30px">Please
                <a href="${pageContext.request.contextPath}/contacts" class="btn btn-info">contact</a>
                web site administrator!</p>

            <a href="${pageContext.request.contextPath}/" class="btn btn-success" style="margin-top: 15px">Home</a>
            <br><br>
        </div>

    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>
</html>