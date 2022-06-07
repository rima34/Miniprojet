<?php
include '../vendor/autoload.php';
use Kreait\Firebase\Factory;
class Equipe
{
    private $name;
    private $uid;
    protected $database;
    protected $dbname = 'equipe';
    public function __construct()
    {
        $factory = (new Factory)
            ->withServiceAccount('../secret/eticket-c74ca-7136fb62c3bc.json')
            ->withDatabaseUri('https://eticket-c74ca-default-rtdb.firebaseio.com');

        $this->database = $factory->createDatabase();
    }


    public function getNameEquipe()
    {
        return $this->name;
    }

    public function getUidEquipe()
    {
        return $this->uid;
    }

    public function setNameEquipe($name)
    {
        $this->name = $name;
    }

    public function setUidEquipe($uid)
    {
        $this->uid = $uid;
    }

    public function getAllEquipe()
    {
        if ($this->database->getReference($this->dbname)->getSnapshot()) {
            return $this->database->getReference($this->dbname)->getValue();
        } else {
            return false;
        }
    }

    public function insert($data)
    {
        if (empty($data) || !isset($data)) {
            return false;
        }
        foreach ($data as $key => $value) {
            $this->database->getReference()->getChild($this->dbname)->push()->getChild($key)->set($value);
        }
    }
    public function delete($equipeId)
    {
        if (empty($equipeId) || !isset($equipeId)) {
            return false;
        }
        if ($this->database->getReference($this->dbname)->getSnapshot()->getChild($equipeId)->exists()) {
            $this->database->getReference($this->dbname)->getChild($equipeId)->remove();
            return true;
        } else {
            return false;
        }
    }
    public function update($equipeId,$newValue)
    {
        if (empty($equipeId) || !isset($equipeId)) {
            return false;
        }
        if ($this->database->getReference($this->dbname)->getSnapshot()->getChild($equipeId)->exists()) {
            $this->database->getReference($this->dbname)->getChild($equipeId)->set($newValue);
            return true;
        } else {
            return false;
        }
    }


    
    public function get($equipeID)
    {
        if (empty($equipeID) || !isset($equipeID)) {
            return false;
        }
        if ($this->database->getReference($this->dbname)->getSnapshot()->hasChild($equipeID)) {
            return $this->database->getReference($this->dbname)->getChild($equipeID)->getValue();
        } else {
            return false;
        }
    }
}


