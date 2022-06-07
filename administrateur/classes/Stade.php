<?php
include '../vendor/autoload.php';

use Kreait\Firebase\Factory;

class Stade
{
    private $name;
    private $uid;
    protected $database;
    protected $dbname = 'stade';
    public function __construct()
    {
        $factory = (new Factory)
            ->withServiceAccount('../secret/eticket-c74ca-7136fb62c3bc.json')
            ->withDatabaseUri('https://eticket-c74ca-default-rtdb.firebaseio.com');

        $this->database = $factory->createDatabase();
    }


    public function getNameStade()
    {
        return $this->name;
    }

    public function getUidStade()
    {
        return $this->uid;
    }

    public function setNameStade($name)
    {
        $this->name = $name;
    }

    public function setUidStade($uid)
    {
        $this->uid = $uid;
    }

    public function getAllStade()
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
    public function delete($StadeId)
    {
        if (empty($StadeId) || !isset($StadeId)) {
            return false;
        }
        if ($this->database->getReference($this->dbname)->getSnapshot()->getChild($StadeId)->exists()) {
            $this->database->getReference($this->dbname)->getChild($StadeId)->remove();
            return true;
        } else {
            return false;
        }
    }
    public function update($StadeId,$newValue)
    {
        if (empty($StadeId) || !isset($StadeId)) {
            return false;
        }
        if ($this->database->getReference($this->dbname)->getSnapshot()->getChild($StadeId)->exists()) {
            $this->database->getReference($this->dbname)->getChild($StadeId)->set($newValue);
            return true;
        } else {
            return false;
        }
    }
    
    public function get($stadeID)
    {
        if (empty($stadeID) || !isset($stadeID)) {
            return false;
        }
        if ($this->database->getReference($this->dbname)->getSnapshot()->hasChild($stadeID)) {
            return $this->database->getReference($this->dbname)->getChild($stadeID)->getValue();
        } else {
            return false;
        }
    }
}


