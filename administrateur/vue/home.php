<?php
include '../classes/Matchs.php';
include '../classes/Equipe.php';
include '../classes/Stade.php';
$matchs = new Matchs();
$equips = new Equipe();
$stades = new Stade();
$allMatchs = $matchs->getAllMatchs();

if(!empty($_GET['uid'])){
  $match = new Matchs();
  $match->delete($_GET['uid']);
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
          <a class="nav-link" href="ajouter_match.php">Ajouter Match </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="gestion_equipe.php">Gestion Equipe</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="ajouter_stade.php">Gestion Stade</a>
        </li>
      </ul>
      <form class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="text" placeholder="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
    </div>
  </nav>





  <div class="container_fluide">
    <div class="row justify-content-center">
      <div class="col-md-10 mt-3">
        <table class="table table-hover ">
          <thead>
            <tr>
              <th scope="col">Equipe 1</th>
              <th scope="col">Equipe 2</th>
              <th scope="col">Date </th>
              <th scope="col">Time </th>
              <th scope="col">Prix</th>
              <th scope="col">Stade</th>
              <th scope="col">Controle</th>
            </tr>
          </thead>
          <tbody>
            <?php
            if (!empty($allMatchs)) {
              foreach ($allMatchs as $key => $value) {
            ?>
                <tr>
                  <td> <?php $currentEquipe = $equips->get($value['equipe1']);
                        echo $value['equipe1'];
                        ?> </td>
                  <td> <?php $currentEquipe = $equips->get($value['equipe2']);
                       echo $value['equipe2'];
                        ?> </td>
                  <td> <?php echo $value['date']; ?> </td>
                  <td> <?php echo $value['time']; ?> </td>
                  <td> <?php echo $value['prix']; ?> DT</td>
                  <td> <?php $currentStade = $stades->get($value['stade']);
                        echo $value['stade'];
                        ?></td>
                  <td>
                  <a href="#"><button class="btn btn-outline-warning">Modifier</button></a>
                    <a href="home.php?uid=<?php echo $key; ?>"><button class="btn btn-outline-danger">Supprimer</button></a>
                  </td>
                </tr>
            <?php }
            } ?>
          </tbody>
        </table>

      </div>
    </div>
  </div>


  <script src="../js/jquery.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>

</body>

</html>