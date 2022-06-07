<?php
include '../classes/Stade.php';
$stade = new Stade();
$allStade = $stade->getAllStade();

if (!empty($_GET['action']) && !empty($_GET['uid'])) {
    if ($_GET['action'] == "delete") {
        $stade = new Stade();
        $stade->delete($_GET['uid']);
        header("location:ajouter_stade.php");
    }
    /*  if($_GET['action']=="update" && !empty($_GET['dataUpdate'])){
      echo $_GET['dataUpdate'];
    } */
}

if (!empty($_POST['nameStade'])) {
    $stade->insert([
        "stade" => $_POST['nameStade']
    ]);
    header("location:ajouter_stade.php");
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
                    <a class="nav-link" href="ajouter_match.php">Ajouter Match</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ajouter_stade.php">Ajouter Stade</a>
                </li>

            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="text" placeholder="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
    </nav>



    <div class="contianer_fluide">
        <div class="row justify-content-center">
            <div class="col-md-4  mt3">
                <form class="form-inline mt-3" action="ajouter_stade.php" method="POST">
                    <div class="form-group mx-sm-3 mb-2">
                        <input type="text" class="form-control" id="" value="<?php
                                                                                if (!empty($_GET['dataUpdate'])) {
                                                                                    echo $_GET['dataUpdate'];
                                                                                }
                                                                                ?>" name="nameStade" placeholder="Name Stade">
                    </div>
                    <button type="submit" class="btn btn-outline-primary mb-2"><?php
                                                                                if (!empty($_GET['dataUpdate'])) {
                                                                                    echo "Modifer Stade";
                                                                                }else{
                                                                                    echo "Ajouter Stade";
                                                                                }
                                                                                ?></button>
                </form>
                <table class="table table-hover ">
                    <thead>
                        <tr>
                            <th scope="col">Name Stade</th>

                            <th scope="col">Controle</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php
                        if(!empty($allStade)){foreach ($allStade as $key => $value) {

                        ?>
                            <tr>
                                <td> <?php echo $value['stade']; ?> </td>

                                <td>
                                    <a href="ajouter_stade.php?uid=<?php echo $key; ?>&dataUpdate=<?php echo $value['stade']; ?>&action=update"><button class="btn btn-outline-warning">Modifier</button></a>
                                    <a href="ajouter_stade.php?uid=<?php echo $key; ?>&action=delete"><button class="btn btn-outline-danger">Supprimer</button></a>

                                </td>


                            </tr>
                        <?php }} ?>
                    </tbody>
                </table>


            </div>
        </div>
    </div>







    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>

</html>