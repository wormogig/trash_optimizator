<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>



    <title>TrashOptimizator</title>
    <!--style for maps-->
    <style>
        #map {
            height: 70% !important;
            /*position: absolute !important;*/
            /*overflow: visible !important;*/
        }

        .ml-btn {
            position: absolute;
            top: 150px;
            left: 45px;
        }

        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
    </style>
</head>


<body>

<!--menu-->
<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
    <div class="container-fluid">
        <a href="#" class="navbar-brand">TRASHOPTIMIZATOR</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a href="/" class="nav-link">О нас</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!--grid map and right-bar-->
<div class="container-fluid">
    <div class="row">
        <!--maps-->
        <div class="col-md-10" style="height: 780px">
            <div id="map"></div>
        </div>
        <div id="bar" class="col-md-2">
<%--            <img src="https://www.flaticon.com/premium-icon/icons/svg/1991/1991033.svg" class="img-fluid"--%>
<%--                 class="img-responsive">--%>
<%--            <ul id="data_list">--%>
<%--            </ul>--%>
<%--            <title>data</title>--%>


            <div class="card border-dark mb-3" style="max-width: 18rem;">
                <div class="card-header">Инструкция</div>
                <div class="card-body text-dark">
                    <h5 class="card-title">Как это работает</h5>
                    <p class="card-text"> 1) Выделяем область с мусором. <br> 2) Указываем количество урн. <br> 3)
                        Расчитываем расположение.</p>
                </div>
            </div>
            <button style="position: absolute;z-index: 100;" type="button" class="btn btn-outline-secondary"
                    data-toggle="modal" data-target="#exampleModal2" data-whatever="@mdo2" onclick="garbageCount()" disabled id="garbageCalc">
                Расчитать
            </button>
            <button style="position: absolute;z-index: 100;" type="button" class="btn btn-outline-secondary"
                    data-toggle="modal" data-whatever="@mdo2" onclick="urnsSend()" id="urnsSend">
                Отправить
            </button>
            <div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel2"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel2">Расставить урны</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group">
                                    <label for="garbageCount" class="col-form-label">Количество точек мусора в выбранной
                                        области:</label>
                                    <input type="text" class="form-control" id="garbageCount" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="urnCount" class="col-form-label">Количество доступных урн для
                                        установки:</label>
                                    <input type="number" min="1" class="form-control" required="required"
                                           placeholder="Здесь вводим количество доступных урн"
                                           id="urnCount">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                            <button onclick="sendPoints()" type="button" class="btn btn-primary">Расчитать</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="data.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC_0hHjpGU0ktipMMfo4CXMcqmeDBLANWI&libraries=drawing,geometry&callback=initMap"
        async defer></script>

</body>
</html>