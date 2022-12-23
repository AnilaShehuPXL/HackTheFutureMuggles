package be.htf.spellbackend.services.impl;

import be.htf.spellbackend.domain.*;
import be.htf.spellbackend.domain.dto.PrivateProblemDto;
import be.htf.spellbackend.domain.dto.PrivateSpellDto;
import be.htf.spellbackend.domain.dto.QuestDto;
import be.htf.spellbackend.services.ISpellService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class SpellServiceImpl implements ISpellService {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private final HashMap<String, SpellType> recipes;
    private final HashMap<String, SpellType> spellIds;
    public SpellServiceImpl() {
        recipes = new HashMap<>();
        spellIds = new HashMap<>();
        recipes.put("Decode the following string", SpellType.MORSE);
        recipes.put("What is the Perfect Number in the nth position (10-based)?", SpellType.PERFECT_NUMBER);
        recipes.put("Convert the following String to hexadecimal value (Use a space delimiter between values)Example: 'testAnswer' equates to '74 65 73 74 41 6e 73 77 65 72'", SpellType.HEXADECIMAL);
        recipes.put("The following string is encrypted with an ancient algorithm, invented in one of the mightiest empires of all time.Tip: one of the leaders of this empire.", SpellType.CAESER_SUBSTITE);
        recipes.put("Find the missing numbers in the sequence. Return them as a comma-separated string", SpellType.FIBONACCI);
        recipes.put("Replace string at given index in arrayList with the replacement, and return the whole array.", SpellType.STRING_REPLACE);
        recipes.put("Find all the primes between the start and end index (both inclusive), Answer is a comma-separated array, ex: [2,3,5,...]", SpellType.FIND_PRIME);
        recipes.put("Answer is the first and last day of the month (1 = january) eg: MONDAY-FRIDAY", SpellType.DAY_OF_MONTH);
        recipes.put("Brute force the password. It can contain upper/lowercase alphanumeric characters", SpellType.BRUTE_FORCE_PASSWORD);
        recipes.put("Is the following string in alphabetical order? (User N or Y as answer)", SpellType.ALPHABETIC);
    }

    private SpellType returnSpellType(String spellId) {
        return spellIds.get(spellId);
    }

    @Override
    public void setQuest(QuestDto quest) {
        List<PrivateProblemDto> problems = quest.getProblems();
        for (PrivateProblemDto problem : problems) {
            List<PrivateSpellDto> spells = problem.getSpells();
            for (PrivateSpellDto spell : spells) {
                SpellType spellType = recipes.get(spell.getRecipe());
                spellIds.put(spell.getId(), spellType);
            }
        }
    }

    @Override
    public String findFormula(String spellId, String ingredient) {
        SpellType spellType = spellIds.get(spellId);
        switch (spellType) {
            case MORSE -> {
                return solveMorse(ingredient);
            }
            case PERFECT_NUMBER -> {
                return perfectNumber(ingredient);
            }
            case HEXADECIMAL -> {
                return hexaDecimal(ingredient);
            }
            case CAESER_SUBSTITE -> {
                return caeser(ingredient);
            }
            case FIBONACCI -> {
                return fibonacci(ingredient);
            }
            case STRING_REPLACE -> {
                return stringReplace(ingredient);
            }
            case FIND_PRIME -> {
                return findPrime(ingredient);
            }
            case DAY_OF_MONTH -> {
                return dayOfMonth(ingredient);
            }
            case BRUTE_FORCE_PASSWORD -> {
                return bruteForce(ingredient);
            }
            default -> {
                return null;
            }
        }
    }

    private String bruteForce(String ingredient) {
        return null;
    }

    private String dayOfMonth(String ingredient) {
        return null;
    }

    private String findPrime(String ingredient) {
        Gson gson = new Gson();
        PrimeIngredient primeIngredient = gson.fromJson(ingredient, PrimeIngredient.class);
        int start = primeIngredient.getStart();
        int end = primeIngredient.getEnd();
        ArrayList<Integer> resultArray = findAllPrimes(start, end);
        return resultArray.toString();
    }

    private String stringReplace(String ingredient) {
        return null;
    }

    private String fibonacci(String ingredient) {
        return null;
    }

    private String caeser(String ingredient) {
        return decrypt(ingredient, 3);
    }

    private String hexaDecimal(String ingredient) {
        return "hexa";
    }

    private String perfectNumber(String ingredient) {
        return "perfectNumber";
    }

    private String solveMorse(String ingredient) {
        return "morse";
    }

    private ArrayList<Integer> findAllPrimes(int start, int end) {
        ArrayList<Integer> primes = new ArrayList<>();

        for(int i = start; i <= end; i++) {
            boolean isPrime = true;
            int k = i - 1;
            for (int j = 2; j < k; j++){
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }
        return primes;

    }

    public static String decrypt(String cipherText, int shiftKey) {
        cipherText = cipherText.toLowerCase();
        StringBuilder message = new StringBuilder();
        for (int ii = 0; ii < cipherText.length(); ii++) {
            int charPosition = ALPHABET.indexOf(cipherText.charAt(ii));
            int keyVal = (charPosition - shiftKey) % 26;
            if (keyVal < 0) {
                keyVal = ALPHABET.length() + keyVal;
            }
            char replaceVal = ALPHABET.charAt(keyVal);
            message.append(replaceVal);
        }
        return message.toString();
    }
}
