<?php

    include 'classes/Admin.php';
    if(!empty($_POST['userName'])||!empty($_POST['password'])){
        $admin=new Admin();
    
        if($admin->getDataAdmin()){
            foreach ($admin->getDataAdmin() as $key=> $value) {
                if($key=="username"){
                    $admin->setUserName($value);
                }else if($key=="password"){
                    $admin->setPassword($value);
                }
            }
            if($admin->getUserName()==$_POST['userName'] && $admin->getPassword()==$_POST['password']){
                header("location:vue/home.php");
            }else{
                print("Error login");
            }

        }else{
            header("location:index.php");
        }
    }
?>




<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/loginStyle.css">
    <title>E-TICKET</title>
</head>

<body>


    <div class="container-fluide">
        <div class="row justify-content-center">
            <div class="col-md-12 ">
                <div class="wrapper">
                    <div class="logo"> <img src="img/tk.png" alt=""> </div>
                    <div class="text-center mt-4 name">E-TICKET</div>
                    <form class="p-3 mt-3" action="index.php" method="post">
                        <div class="form-field d-flex align-items-center">
                            <span class="far fa-user"></span> <input type="text" name="userName" id="userName"
                                placeholder="Username">
                        </div>
                        <div class="form-field d-flex align-items-center"> <span class="fas fa-key"></span> <input
                                type="password" name="password" id="pwd" placeholder="Password"> </div>
                        <button type="submit" class="btn mt-3">Login</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>