import requests
import json

# The URL endpoint exposed by your Spring Boot application
BASE_URL = "http://localhost:8080/api/flashcards"

def test_create_flashcard():
    print("--- 1. Testing POST (Create Flashcard) ---")
    
    # Mock vocabulary data
    card_data = {
        "frontText": "Hola",
        "backText": "Hello",
        "reviewIntervalDays": 1,
        "easeFactor": 2.5
    }
    
    headers = {"Content-Type": "application/json"}
    
    # Send the data to your Spring Boot REST API
    response = requests.post(BASE_URL, data=json.dumps(card_data), headers=headers)
    
    if response.status_code == 200 or response.status_code == 201:
        print("Success! Flashcard saved to database via API.")
        print(f"Response from server: {response.json()}\n")
        return response.json()["id"]
    else:
        print(f"Failed! Server responded with status code: {response.status_code}")
        print(response.text)
        return None

def test_get_all_flashcards():
    print("--- 2. Testing GET (Retrieve Flashcards) ---")
    response = requests.get(BASE_URL)
    
    if response.status_code == 200:
        cards = response.json()
        print(f"Success! Retrieved {len(cards)} flashcard(s) from database:")
        for card in cards:
            print(f" -> ID {card['id']}: {card['frontText']} = {card['backText']}")
    else:
        print(f"Failed to fetch cards. Status: {response.status_code}")

if __name__ == "__main__":
    # Run the tests sequentially
    card_id = test_create_flashcard()
    if card_id:
        test_get_all_flashcards()
