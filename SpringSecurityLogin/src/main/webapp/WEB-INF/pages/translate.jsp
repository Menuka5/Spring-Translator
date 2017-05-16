<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Translator window</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script>
        $(document).ready(function(){
            $.ajax({
                type: "GET",
                url:"sendAllLanguages",
                dataType: "json",
                success: function (data) {
                    var $el = $("#Fromlangs");
                    var $tl = $("#Tolangs");
                    $el.empty();
                    $tl.empty();
                    $.each(data, function(value, key) {
                        $el.append($("<option></option>").attr("value", value).text(key));
                        $tl.append($("<option></option>").attr("value", value).text(key));
                    });
                }
            });


            $("button").click(function(e) {
                var fromLang =$("#Fromlangs").val();
                var toLang =$("#Tolangs").val();
                var textToTranslate =$("#textToTranslate").val();

                $.ajax({
                    type: "GET",
                    url:"getTranslate",
                    data: {
                        fromLang: fromLang,
                        toLang: toLang,
                        text: textToTranslate
                    },
                    dataType: "json",
                    success: function (reply) {
                        $("#translated").val("");
                        var input = $("#translated");
                        input.val(input.val() + reply.text);
                    }
                });

            });
        });
    </script>
</head>

<body>

<center><h1>Translator</h1></center>
<a href="<c:url value="/logout" />">Logout</a>
<br>
Input Language <input id="textToTranslate" placeholder="Enter Text to translate">
<select id="Fromlangs"></select>
<br>
Output Language<input id="translated" placeholder="Translated text">
<select id="Tolangs"></select>

<button>Translate</button>

</body>
</html>
