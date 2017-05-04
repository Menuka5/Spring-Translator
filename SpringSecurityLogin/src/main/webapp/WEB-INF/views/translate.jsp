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
                url:"https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=trnsl.1.1.20160607T111835Z.f64d4276fb712ae3.ed0f150b6046b34df73301472d5e576d32fe3c8b",
                dataType: "json",
                success: function (data) {
                    var $el = $("#Fromlangs");
                    var $tl = $("#Tolangs");
                    $el.empty();
                    $tl.empty();
                    $.each(data.langs, function(value, key) {
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
                    url:"https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20160607T111835Z.f64d4276fb712ae3.ed0f150b6046b34df73301472d5e576d32fe3c8b&text="+textToTranslate+"&lang="+fromLang+"-"+toLang,
                    dataType: "json",
                    success: function (reply) {
                        $("#translated").val("");
                        var input = $("#translated");
                        input.val(input.val() + reply.text[0]);
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
