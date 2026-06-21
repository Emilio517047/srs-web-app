import requests
import json
import time

# Endpoints
DICTIONARY_API_URL = "https://api.dictionaryapi.dev/api/v2/entries/en"
SPRING_BOOT_API_URL = "http://localhost:8080/api/flashcards"

RESPONSE_200 = 200
SUCCESSFUL_STATUS_CODES = [200, 201, 202]

VOCAB_LIST = ["Dog", "Cat", "Bird", "Fish"]

def fetch_definition(word):
    """Fetches the definition of an English word from the public Dictionary API."""
    print(f"Fetching definition for: '{word}'...")
    response = requests.get(f"{DICTIONARY_API_URL}/{word}")
    
    if response.status_code == RESPONSE_200:
        data = response.json()
        try:
            # Extract the first definition from the API response payload
            definition = data[0]['meanings'][0]['definitions'][0]['definition']
            return definition
        except (IndexError, KeyError):
            print(f"⚠️ Could not parse the definition layout for '{word}'.")
            return None
    else:
        print(f"❌ Word '{word}' not found in the dictionary API (Status: {response.status_code}).")
        return None

def save_to_database(word, definition):
    """Sends the formatted flashcard data to our Spring Boot backend API."""
    card_data = {
        "frontText": word.capitalize(),
        "backText": definition,
        "reviewIntervalDays": 1,
        "easeFactor": 2.5
    }
    
    headers = {"Content-Type": "application/json"}
    response = requests.post(SPRING_BOOT_API_URL, data=json.dumps(card_data), headers=headers)
    
    if response.status_code in SUCCESSFUL_STATUS_CODES:
        print(f"✅ Successfully saved '{word}' to the database!")
    else:
        print(f"❌ Failed to save '{word}' to database. Status: {response.status_code}")

def main():
    print("=== STARTING AUTOMATED VOCABULARY INGESTION ===\n")
    
    for word in VOCAB_LIST:
        definition = fetch_definition(word)
        if definition:
            save_to_database(word, definition)
        
        # Polite scraping practice: pause for 1 second between web requests
        time.sleep(1)
        print("-" * 40)
        
    print("\n=== INGESTION COMPLETE ===")

if __name__ == "__main__":
    main()