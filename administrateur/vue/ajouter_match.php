<?php

include '../classes/Matchs.php';
include '../classes/Equipe.php';
include '../classes/Stade.php';
$matchs = new Matchs();
$equips = new Equipe();
$stade = new Stade();
$allEquipes = $equips->getAllEquipe();
$allStades = $stade->getAllStade();


if (!empty($_POST['equipe1'])&&!empty($_POST['equipe2'])&&!empty($_POST['date_match'])&&!empty($_POST['prix'])&&!empty($_POST['stade'])&&!empty($_POST['time_match'])) {
      $matchs->insert([
        "equipe1" => $_POST['equipe1'],
        "equipe2" => $_POST['equipe2'],
        "date" => $_POST['date_match'],
        "time" => $_POST['time_match'],
        "prix" => $_POST['prix'],
        "stade" => $_POST['stade'], 
    ]);
    header("location:home.php");  
}
?>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/loginStyle.css">
    <link rel="stylesheet" href="../css/defalutStyle.css">
    <title>E-TICKET</title>
</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-light bg-light ">
        <a class="navbar-brand" href="home.php"><img class="logonav" src="../img/tk.png" alt=""></a>
        <button class="navbar-toggler hidden-lg-up" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation"></button>
        <div class="collapse navbar-collapse" id="collapsibleNavId">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item ">
                    <a class="nav-link" href="home.php">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="gestion_equipe.php">Gestion equipe</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="gestion_equipe.php">Gestion stade</a>
                </li>

            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="text" placeholder="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>

        </div>
    </nav>
    </nav>



    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 ">
                <div class="wrapper">
                    <div class="logo"> <img src="../img/tk.png" alt=""> </div>
                    <div class="text-center mt-4 name">E-TICKET</div>
                    <form class="p-3 mt-3" action="ajouter_match.php" method="post">
                        <select class="form-control form-field d-flex align-items-center" name="equipe1" id="">
                            <?php
                            if (!empty($allEquipes)) {
                                foreach ($allEquipes as $key => $value) {
                            ?>
                                    <option value="<?php echo $value['equipe'];?>"><?php echo $value['equipe'];?></option>
                             <?php
                               }
                            }
                            ?>
                        </select>
                        <select class="form-control form-field d-flex align-items-center" name="equipe2" id="">
                            <?php
                            if (!empty($allEquipes)) {
                                foreach ($allEquipes as $key => $value) {
                            ?>
                                    <option value="<?php echo $value['equipe']?>"><?php echo $value['equipe'];?></option>
                            <?php
                                }
                            }
                            ?>
                        </select>
                        
                        <select class="form-control form-field d-flex align-items-center" name="stade" id="">
                            <?php
                            if (!empty($allStades)) {
                                foreach ($allStades as $key => $value) {
                            ?>
                                    <option value="<?php echo $value['stade'];?>"><?php echo $value['stade'];?></option>
                            <?php
                                }
                            }
                            ?>
                        </select>
                        
                        
                        <div class="form-field d-flex align-items-center">
                            <input type="date" name="date_match" id="">  
                        </div>
                        <div class="form-field d-flex align-items-center">
                            <input id="" type="time" name="time_match" >
                        </div>
                        <div class="form-field d-flex align-items-center">
                            <input type="text" name="prix" id="" placeholder="Prix" >
                        </div>
                        <button type="submit" class="btn mt-3">Ajouter</button>
                    </form>
                </div>
            </div>
        </div>
    </div>




</body>

</html>