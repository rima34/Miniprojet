<?php
include 'vendor/autoload.php';
use Kreait\Firebase\Factory;

class Admin
{

    private $username;
    private $password;
    protected $database;
    protected $dbname = 'admin';
    public function __construct()
    {
        $factory = (new Factory)
            ->withServiceAccount('./secret/eticket-c74ca-7136fb62c3bc.json')
            ->withDatabaseUri('https://eticket-c74ca-default-rtdb.firebaseio.com/');

        $this->database = $factory->createDatabase();
       
    }
    public function getDataAdmin()
    {
        if ($this->database->getReference($this->dbname)->getSnapshot()) {
            return $this->database->getReference($this->dbname)->getValue();
        } else {
            return false;
        }
    }

  

    public function getUserName()
    {
        return $this->username;
    }

    public function getPassword()
    {
        return $this->password;
    }

    public function setUserName($username)
    {
        $this->username = $username;
    }

    public function setPassword($password)
    {
        $this->password = $password;
    }

    
}
