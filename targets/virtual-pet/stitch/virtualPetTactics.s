module pet.tactics;
import op "org.sa.rainbow.stitch.lib.*";
import model "PetSys:Acme" { PetSys as M, PetFam as T};

tactic TFeedPet() {
    float hunger = M.pet.hunger;
    condition {
        M.pet.hunger >= M.MAX_HUNGER;
    }
    action {
        M.feed();
    }
    effect @[3000] {
        hunger' < hunger;
    }
}

tactic TRestPet() {
    float fatigue = M.pet.fatigue;
    condition {
        M.pet.fatigue >= M.MAX_FATIGUE;
    }
    action {
        M.rest();
    }
    effect @[3000] {
        fatigue' < fatigue;
    }
}

tactic TPlayPet() {
    float stress = M.pet.stress;
    condition {
        M.pet.stress >= M.MAX_STRESS;
    }
    action {
        M.play();
    }
    effect @[3000] {
        stress' < stress;
    }
}