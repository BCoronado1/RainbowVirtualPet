module pet.strategies;
import op "org.sa.rainbow.stitch.lib.*"; 
import lib "virtualPetTactics.s";
import model "PetSys:Acme" { PetSys as M, PetFam as T};

define boolean petHungry = M.pet.hunger >= M.MAX_HUNGER;
define boolean petTired = M.pet.fatigue >= M.MAX_FATIGUE;
define boolean petStressed = M.pet.stress >= M.MAX_STRESS;

strategy FeedPet [petHungry] {
    t1_feed_pet: (petHungry) -> TFeedPet() @[3000] {
        t1a1_default: (default) -> done;
    }
}

strategy RestPet [petTired] {
    t1_rest_pet: (petTired) -> TRestPet() @[3000] {
        t1a1_default: (default) -> done;
    }
}

strategy PlayPet [petStressed] {
    t1_play_pet: (petStressed) -> TPlayPet() @[3000] {
        t1a1_default: (default) -> done;
    }
}