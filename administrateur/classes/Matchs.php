<?php
require_once '../vendor/autoload.php';

use Kreait\Firebase\Factory;

class Matchs
{
    private $uid;
    private $equipe1_id;
    private $equipe2_id;
    private $lieux;
    private $prix;
    private $date;
    private $time;
    protected $database;
    protected $dbname = 'matchs';
    public function __construct()
    {
        $factory = (new Factory)
            ->withServiceAccount('../secret/eticket-c74ca-7136fb62c3bc.json')
            ->withDatabaseUri('https://eticket-c74ca-default-rtdb.firebaseio.com');

        $this->database = $factory->createDatabase();
    }


    public function getUid()
    {
        return $this->uid;
    }
    public function getEquipe1ID()
    {
        return $this->equipe1_id;
    }
    public function getEquipe2ID()
    {
        return $this->equipe2_id;
    }
    public function getLieux()
    {
        return $this->lieux;
    }
    public function getPrix()
    {
        return $this->prix;
    }
    public function getDate()
    {
        return $this->date;
    }

    public function getTime()
    {
        return $this ->time;
    }



    public function get($matchID)
    {
        if (empty($matchID) || !isset($matchID)) {
            return false;
        }
        if ($this->database->getReference($this->dbname)->getSnapshot()->hasChild($matchID)) {
            return $this->database->getReference($this->dbname)->getChild($matchID)->getValue();
        } else {
            return false;
        }
    }

    public function getAllMatchs()
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
        $this->database->getReference()->getChild($this->dbname)->push()->set($data);
    }
    public function delete($matchID)
    {
        if (empty($matchID) || !isset($matchID)) {
            return false;
        }
        if ($this->database->getReference($this->dbname)->getSnapshot()->hasChild($matchID)) {
            $this->database->getReference($this->dbname)->getChild($matchID)->remove();
            return true;
        } else {
            return false;
        }
    }
}

//$matchs = new matchs();

/* var_dump($matchs->insert(
    //'4'=>array:2[{"test":"abc"},{"test2","cba"}],
    [
        'abc' => 'fff',
        'ccc' => 'ggg',
         'notification3' => [
            'title' => '$qsdfqsdf',
            'body' => '$qsdfqsdf.',
            'icon' => 'gaefqsdf',
            'color' => '#qsdgaer',
            'sound' => 'gqdsfsdf',
        ], 
    ]

  ));*/
 
//var_dump($matchs->get('-MrljBaKRPpuHU55wc_V')); 
//print_r($matchs->getAllMatch());