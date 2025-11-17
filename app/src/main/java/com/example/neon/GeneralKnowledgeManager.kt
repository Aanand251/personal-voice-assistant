package com.example.neon

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import java.util.*

object GeneralKnowledgeManager {
    private var tts: TextToSpeech? = null

    fun init(textToSpeech: TextToSpeech) {
        tts = textToSpeech
    }

    fun canHandle(command: String): Boolean {
        val lowerCommand = command.lowercase(Locale.ROOT)
        return gkData.keys.any { lowerCommand.contains(it) }
    }

    fun handle(context: Context, command: String) {
        val lowerCommand = command.lowercase(Locale.ROOT)
        val answer = gkData.entries.find { lowerCommand.contains(it.key) }?.value
            ?: "Sorry, I don't know about that yet."
        speak(context, answer)
    }

    private fun speak(context: Context, text: String) {
        if (context is MainActivity) {
            context.speak(text)
        } else {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }


    private val gkData = mapOf(
        // Prime Minister of India
        "prime minister of india" to "The Prime Minister of India is Narendra Modi.",
        "who is prime minister of india" to "The Prime Minister of India is Narendra Modi.",
        "current prime minister of india" to "The Prime Minister of India is Narendra Modi.",

        // President of India
        "president of india" to "The President of India is Droupadi Murmu.",
        "who is president of india" to "The President of India is Droupadi Murmu.",
        "current president of india" to "The President of India is Droupadi Murmu.",

        // Capital of India
        "capital of india" to "The capital of India is New Delhi.",
        "what is the capital of india" to "The capital of India is New Delhi.",
        "india capital" to "The capital of India is New Delhi.",

        // Light bulb inventor
        "who invented light bulb" to "The light bulb was invented by Thomas Edison.",
        "light bulb inventor" to "The light bulb was invented by Thomas Edison.",
        "who is the inventor of light bulb" to "The light bulb was invented by Thomas Edison.",

        // Largest planet
        "largest planet" to "The largest planet in our solar system is Jupiter.",
        "biggest planet" to "The largest planet in our solar system is Jupiter.",
        "which is the largest planet" to "The largest planet in our solar system is Jupiter.",

        // Fastest animal
        "fastest animal" to "The fastest animal on land is the cheetah.",
        "fastest land animal" to "The fastest animal on land is the cheetah.",
        "which is the fastest animal" to "The fastest animal on land is the cheetah.",

        // National animal of India
        "national animal of india" to "The national animal of India is the Bengal tiger.",
        "india national animal" to "The national animal of India is the Bengal tiger.",
        "which is national animal of india" to "The national animal of India is the Bengal tiger.",

        // National bird of India
        "national bird of india" to "The national bird of India is the Indian peafowl, also known as the peacock.",
        "india national bird" to "The national bird of India is the Indian peafowl, also known as the peacock.",
        "which is national bird of india" to "The national bird of India is the Indian peafowl, also known as the peacock.",

        // National flower of India
        "national flower of india" to "The national flower of India is the lotus.",
        "india national flower" to "The national flower of India is the lotus.",
        "which is national flower of india" to "The national flower of India is the lotus.",

        // Tallest mountain in the world
        "tallest mountain in the world" to "The tallest mountain in the world is Mount Everest.",
        "highest mountain in the world" to "The tallest mountain in the world is Mount Everest.",
        "which is the tallest mountain" to "The tallest mountain in the world is Mount Everest.",

        // Largest ocean
        "largest ocean" to "The largest ocean on Earth is the Pacific Ocean.",
        "which is the largest ocean" to "The largest ocean on Earth is the Pacific Ocean.",
        "biggest ocean on earth" to "The largest ocean on Earth is the Pacific Ocean.",

        // Deepest ocean
        "deepest ocean" to "The deepest ocean is the Pacific Ocean.",
        "which is the deepest ocean" to "The deepest ocean is the Pacific Ocean.",
        "deepest sea" to "The deepest ocean is the Pacific Ocean.",

        // Fastest bird
        "fastest bird" to "The fastest bird in the world is the peregrine falcon.",
        "which is the fastest bird" to "The fastest bird in the world is the peregrine falcon.",
        "fastest flying bird" to "The fastest bird in the world is the peregrine falcon.",

        // Smallest continent
        "smallest continent" to "The smallest continent in the world is Australia.",
        "which is the smallest continent" to "The smallest continent in the world is Australia.",
        "world's smallest continent" to "The smallest continent in the world is Australia.",

        // Longest river
        "longest river" to "The longest river in the world is the Nile River.",
        "which is the longest river" to "The longest river in the world is the Nile River.",
        "world's longest river" to "The longest river in the world is the Nile River.",

        // Hardest natural substance
        "hardest natural substance" to "The hardest natural substance on Earth is diamond.",
        "hardest thing in the world" to "The hardest natural substance on Earth is diamond.",
        "which is the hardest substance" to "The hardest natural substance on Earth is diamond.",

        // Largest mammal
        "largest mammal" to "The largest mammal in the world is the blue whale.",
        "biggest mammal" to "The largest mammal in the world is the blue whale.",
        "which is the largest mammal" to "The largest mammal in the world is the blue whale.",

        // Lowest temperature recorded on earth
        "lowest temperature recorded on earth" to "The lowest temperature ever recorded on Earth was minus 89.2 degrees Celsius in Antarctica.",
        "coldest place on earth" to "The lowest temperature ever recorded on Earth was minus 89.2 degrees Celsius in Antarctica.",
        "where is the lowest temperature recorded" to "The lowest temperature ever recorded on Earth was minus 89.2 degrees Celsius in Antarctica.",

        // Who discovered gravity
        "who discovered gravity" to "Gravity was discovered by Sir Isaac Newton.",
        "gravity discoverer" to "Gravity was discovered by Sir Isaac Newton.",
        "founder of gravity" to "Gravity was discovered by Sir Isaac Newton.",

        // First man on the moon
        "first man on the moon" to "The first man on the Moon was Neil Armstrong.",
        "who stepped first on moon" to "The first man on the Moon was Neil Armstrong.",
        "who first landed on the moon" to "The first man on the Moon was Neil Armstrong.",

        // Who wrote the national anthem of India
        "who wrote the national anthem of india" to "The National Anthem of India, Jana Gana Mana, was written by Rabindranath Tagore.",
        "author of indian national anthem" to "The National Anthem of India, Jana Gana Mana, was written by Rabindranath Tagore.",
        "who is anthem writer of india" to "The National Anthem of India, Jana Gana Mana, was written by Rabindranath Tagore.",

        // Longest wall
        "longest wall" to "The longest wall is the Great Wall of China.",
        "which is the longest wall" to "The longest wall is the Great Wall of China.",
        "world's longest wall" to "The longest wall is the Great Wall of China.",

        // 1. First President of India
        "first president of india" to "Dr. Rajendra Prasad was the first President of India.",
        "who was india's first president" to "Dr. Rajendra Prasad was the first President of India.",
        "india first president" to "Dr. Rajendra Prasad was the first President of India.",

        // 2. National currency of India
        "national currency of india" to "The national currency of India is the Indian Rupee.",
        "india currency" to "The national currency of India is the Indian Rupee.",
        "currency of india" to "The national currency of India is the Indian Rupee.",

        // 3. National sport of India
        "national sport of india" to "The national sport of India is field hockey.",
        "india national sport" to "The national sport of India is field hockey.",
        "which is india's national sport" to "The national sport of India is field hockey.",

        // 4. Longest river in India
        "longest river in india" to "The longest river in India is the Ganga.",
        "india's longest river" to "The longest river in India is the Ganga.",
        "which is the longest river in india" to "The longest river in India is the Ganga.",

        // 5. Largest desert in the world
        "largest desert in the world" to "The largest desert in the world is the Sahara Desert.",
        "which is the largest desert" to "The largest desert in the world is the Sahara Desert.",
        "world's biggest desert" to "The largest desert in the world is the Sahara Desert.",

        // 6. Largest island in the world
        "largest island in the world" to "The largest island in the world is Greenland.",
        "which is the largest island" to "The largest island in the world is Greenland.",
        "world's biggest island" to "The largest island in the world is Greenland.",

        // 7. Largest country by area
        "largest country by area" to "Russia is the largest country in the world by area.",
        "which is the biggest country" to "Russia is the largest country in the world by area.",
        "world's largest country" to "Russia is the largest country in the world by area.",

        // 8. Largest democracy
        "largest democracy" to "India is the largest democracy in the world.",
        "which country is largest democracy" to "India is the largest democracy in the world.",
        "biggest democracy country" to "India is the largest democracy in the world.",

        // 9. Smallest country in the world
        "smallest country in the world" to "The smallest country in the world is Vatican City.",
        "which is the smallest country" to "The smallest country in the world is Vatican City.",
        "world's smallest nation" to "The smallest country in the world is Vatican City.",

        // 10. Highest waterfall in the world
        "highest waterfall in the world" to "Angel Falls in Venezuela is the highest waterfall in the world.",
        "which is the tallest waterfall" to "Angel Falls in Venezuela is the highest waterfall in the world.",
        "world's highest waterfall" to "Angel Falls in Venezuela is the highest waterfall in the world.",

        // 11. Tallest building in the world
        "tallest building in the world" to "The tallest building in the world is Burj Khalifa in Dubai.",
        "which is world's tallest building" to "The tallest building in the world is Burj Khalifa in Dubai.",
        "biggest skyscraper" to "The tallest building in the world is Burj Khalifa in Dubai.",

        // 12. Oldest university in the world
        "oldest university in the world" to "The oldest university in the world is the University of Bologna, Italy.",
        "which is the oldest university" to "The oldest university in the world is the University of Bologna, Italy.",
        "world's oldest university" to "The oldest university in the world is the University of Bologna, Italy.",

        // 13. Largest bone in the human body
        "largest bone in human body" to "The largest bone in the human body is the femur.",
        "biggest bone" to "The largest bone in the human body is the femur.",
        "which is the largest bone" to "The largest bone in the human body is the femur.",

        // 14. Strongest muscle in the human body
        "strongest muscle in human body" to "The masseter (jaw muscle) is the strongest muscle in the human body.",
        "which is the strongest muscle" to "The masseter (jaw muscle) is the strongest muscle in the human body.",
        "strongest body muscle" to "The masseter (jaw muscle) is the strongest muscle in the human body.",

        // 15. Chemical symbol for gold
        "chemical symbol for gold" to "The chemical symbol for gold is Au.",
        "gold symbol in periodic table" to "The chemical symbol for gold is Au.",
        "what is gold's chemical symbol" to "The chemical symbol for gold is Au.",

        // 16. Chemical symbol for silver
        "chemical symbol for silver" to "The chemical symbol for silver is Ag.",
        "silver symbol in periodic table" to "The chemical symbol for silver is Ag.",
        "what is silver's chemical symbol" to "The chemical symbol for silver is Ag.",

        // 17. First war of independence in India
        "first war of independence in india" to "The first war of independence in India occurred in 1857.",
        "india's first independence war" to "The first war of independence in India occurred in 1857.",
        "when was india's first rebellion" to "The first war of independence in India occurred in 1857.",

        // 18. Author of Harry Potter
        "author of harry potter" to "The author of the Harry Potter series is J.K. Rowling.",
        "who wrote harry potter" to "The author of the Harry Potter series is J.K. Rowling.",
        "harry potter book writer" to "The author of the Harry Potter series is J.K. Rowling.",

        // 19. Largest gland in human body
        "largest gland in human body" to "The largest gland in the human body is the liver.",
        "which is the largest gland" to "The largest gland in the human body is the liver.",
        "biggest gland" to "The largest gland in the human body is the liver.",

        // 20. Father of Indian Constitution
        "father of indian constitution" to "Dr. B. R. Ambedkar is called the father of the Indian Constitution.",
        "who is father of indian constitution" to "Dr. B. R. Ambedkar is called the father of the Indian Constitution.",
        "indian constitution father" to "Dr. B. R. Ambedkar is called the father of the Indian Constitution.",

        // 21. Highest civilian award in India
        "highest civilian award in india" to "The highest civilian award in India is the Bharat Ratna.",
        "india's highest civilian award" to "The highest civilian award in India is the Bharat Ratna.",
        "what is india's top civilian award" to "The highest civilian award in India is the Bharat Ratna.",

        // 22. First Prime Minister of India
        "first prime minister of india" to "The first Prime Minister of India was Jawaharlal Nehru.",
        "india's first prime minister" to "The first Prime Minister of India was Jawaharlal Nehru.",
        "who was first pm of india" to "The first Prime Minister of India was Jawaharlal Nehru.",

        // 23. Mother Teresa birthplace
        "mother teresa birthplace" to "Mother Teresa was born in Skopje, now capital of North Macedonia.",
        "where was mother teresa born" to "Mother Teresa was born in Skopje, now capital of North Macedonia.",
        "birthplace of mother teresa" to "Mother Teresa was born in Skopje, now capital of North Macedonia.",

        // 24. Who discovered penicillin
        "who discovered penicillin" to "Penicillin was discovered by Alexander Fleming.",
        "penicillin discoverer" to "Penicillin was discovered by Alexander Fleming.",
        "who found penicillin" to "Penicillin was discovered by Alexander Fleming.",

        // 25. Who invented television
        "who invented television" to "Television was invented by John Logie Baird.",
        "television inventor" to "Television was invented by John Logie Baird.",
        "TV inventor" to "Television was invented by John Logie Baird.",

        // 26. First man in space
        "first man in space" to "The first man in space was Yuri Gagarin.",
        "who was first man in space" to "The first man in space was Yuri Gagarin.",
        "first human in space" to "The first man in space was Yuri Gagarin.",

        // 27. First artificial satellite
        "first artificial satellite" to "Sputnik 1 was the first artificial satellite launched into space.",
        "what was first satellite" to "Sputnik 1 was the first artificial satellite launched into space.",
        "who launched first satellite" to "Sputnik 1 was the first artificial satellite launched into space.",

        // 28. Second largest country by area
        "second largest country in the world" to "Canada is the second largest country in the world by area.",
        "which is second largest country" to "Canada is the second largest country in the world by area.",
        "world's second biggest country" to "Canada is the second largest country in the world by area.",

        // 29. Biggest flower in the world
        "biggest flower in the world" to "Rafflesia arnoldii produces the world's largest flower.",
        "largest flower" to "Rafflesia arnoldii produces the world's largest flower.",
        "which is biggest flower" to "Rafflesia arnoldii produces the world's largest flower.",

        // 30. Longest English word
        "longest english word" to "The longest word in English is pneumonoultramicroscopicsilicovolcanoconiosis.",
        "which is longest english word" to "The longest word in English is pneumonoultramicroscopicsilicovolcanoconiosis.",
        "biggest word in english" to "The longest word in English is pneumonoultramicroscopicsilicovolcanoconiosis.",

        // 31. Longest highway in the world
        "longest highway in the world" to "The Trans-Siberian Highway is the longest highway in the world.",
        "which is world's longest highway" to "The Trans-Siberian Highway is the longest highway in the world.",
        "world's biggest highway" to "The Trans-Siberian Highway is the longest highway in the world.",

        // 32. Most spoken language in the world
        "most spoken language in the world" to "English is the most spoken language by total speakers; Mandarin by native speakers.",
        "which language is spoken most" to "English is the most spoken language by total speakers; Mandarin by native speakers.",
        "world's most spoken language" to "English is the most spoken language by total speakers; Mandarin by native speakers.",

        // 33. First computer programmer
        "first computer programmer" to "Ada Lovelace is considered the first computer programmer.",
        "who was first programmer" to "Ada Lovelace is considered the first computer programmer.",
        "who invented computer programming" to "Ada Lovelace is considered the first computer programmer.",

        // 34. Currency of Japan
        "currency of japan" to "The currency of Japan is the Yen.",
        "japan money" to "The currency of Japan is the Yen.",
        "what currency does japan use" to "The currency of Japan is the Yen.",

        // 35. Currency of USA
        "currency of usa" to "The currency of the United States is the Dollar.",
        "us currency" to "The currency of the United States is the Dollar.",
        "what currency is used in usa" to "The currency of the United States is the Dollar.",

        // 36. City of seven hills
        "city of seven hills" to "Rome is known as the City of Seven Hills.",
        "which city is called seven hills" to "Rome is known as the City of Seven Hills.",
        "Rome city nickname" to "Rome is known as the City of Seven Hills.",

        // 37. Pink city of India
        "pink city of india" to "Jaipur is known as the Pink City of India.",
        "which city is pink city in india" to "Jaipur is known as the Pink City of India.",
        "jaipur city nickname" to "Jaipur is known as the Pink City of India.",

        // 38. City of lakes
        "city of lakes" to "Udaipur is known as the City of Lakes.",
        "which city is called city of lakes" to "Udaipur is known as the City of Lakes.",
        "udaipur city nickname" to "Udaipur is known as the City of Lakes.",

        // 39. Sun rises in which direction
        "sun rises in which direction" to "The sun rises in the east.",
        "sunrise direction" to "The sun rises in the east.",
        "from which direction sun comes" to "The sun rises in the east.",

        // 40. Tallest statue in the world
        "tallest statue in the world" to "The Statue of Unity in India is the tallest statue in the world.",
        "which is tallest statue" to "The Statue of Unity in India is the tallest statue in the world.",
        "world's largest statue" to "The Statue of Unity in India is the tallest statue in the world.",

        // 41. First Indian to win Nobel Prize
        "first indian nobel laureate" to "Rabindranath Tagore was the first Indian to win the Nobel Prize.",
        "first indian nobel prize winner" to "Rabindranath Tagore was the first Indian to win the Nobel Prize.",
        "who was first indian nobel prize winner" to "Rabindranath Tagore was the first Indian to win the Nobel Prize.",

        // 42. Bone making human forehead
        "bone making up human forehead" to "The frontal bone makes up the human forehead.",
        "which bone forms forehead" to "The frontal bone makes up the human forehead.",
        "forehead bone" to "The frontal bone makes up the human forehead.",

        // 43. Largest continent
        "largest continent" to "Asia is the largest continent in the world.",
        "which is the largest continent" to "Asia is the largest continent in the world.",
        "world's biggest continent" to "Asia is the largest continent in the world.",

        // 44. Who invented computer
        "who invented computer" to "Charles Babbage is known as the father of the computer.",
        "computer inventor" to "Charles Babbage is known as the father of the computer.",
        "who is father of computer" to "Charles Babbage is known as the father of the computer.",

        // 45. Who discovered America
        "who discovered america" to "Christopher Columbus is credited with discovering America.",
        "america discovered by whom" to "Christopher Columbus is credited with discovering America.",
        "who found america" to "Christopher Columbus is credited with discovering America.",

        // 46. Which planet is called red planet
        "which planet is called red planet" to "Mars is called the Red Planet.",
        "red planet" to "Mars is called the Red Planet.",
        "planet known as red planet" to "Mars is called the Red Planet.",

        // 47. Who wrote Romeo and Juliet
        "who wrote romeo and juliet" to "Romeo and Juliet was written by William Shakespeare.",
        "romeo and juliet author" to "Romeo and Juliet was written by William Shakespeare.",
        "who is author of romeo and juliet" to "Romeo and Juliet was written by William Shakespeare.",

        // 48. Father of Nation India
        "father of nation india" to "Mahatma Gandhi is called the Father of the Nation in India.",
        "who is father of india" to "Mahatma Gandhi is called the Father of the Nation in India.",
        "india's father of nation" to "Mahatma Gandhi is called the Father of the Nation in India.",

        // 49. Longest bone in human body
        "longest bone in human body" to "The femur is the longest bone in the human body.",
        "which is the longest bone" to "The femur is the longest bone in the human body.",
        "biggest bone in human body" to "The femur is the longest bone in the human body.",

        // 50. Largest organ in human body
        "largest organ in human body" to "The skin is the largest organ of the human body.",
        "biggest organ in human" to "The skin is the largest organ of the human body.",
        "which is largest human organ" to "The skin is the largest organ of the human body.",

        // 51. Fastest land animal
        "fastest land animal" to "The cheetah is the fastest land animal.",
        "which is the fastest animal on land" to "The cheetah is the fastest land animal.",
        "fastest animal on land" to "The cheetah is the fastest land animal.",

        // 52. Smallest continent
        "smallest continent" to "Australia is the smallest continent in the world.",
        "which is smallest continent" to "Australia is the smallest continent in the world.",
        "world's smallest continent" to "Australia is the smallest continent in the world.",

        // 53. Largest desert
        "largest desert in the world" to "The Sahara Desert is the largest desert in the world.",
        "which is largest desert" to "The Sahara Desert is the largest desert in the world.",
        "world's biggest desert" to "The Sahara Desert is the largest desert in the world.",

        // 54. Largest ocean
        "largest ocean" to "The Pacific Ocean is the largest ocean on Earth.",
        "which is largest ocean" to "The Pacific Ocean is the largest ocean on Earth.",
        "world's biggest ocean" to "The Pacific Ocean is the largest ocean on Earth.",

        // 55. How many bones in adult human body
        "how many bones in adult human body" to "The adult human body has 206 bones.",
        "number of bones in human body" to "The adult human body has 206 bones.",
        "total bones in adult body" to "The adult human body has 206 bones.",

        // 56. Largest planet
        "largest planet in solar system" to "Jupiter is the largest planet in our solar system.",
        "biggest planet" to "Jupiter is the largest planet in our solar system.",
        "which is largest planet" to "Jupiter is the largest planet in our solar system.",

        // 57. Fastest bird
        "fastest bird" to "The peregrine falcon is the fastest bird in the world.",
        "which is fastest bird" to "The peregrine falcon is the fastest bird in the world.",
        "fastest flying bird" to "The peregrine falcon is the fastest bird in the world.",

        // 58. Capital of Australia
        "capital of australia" to "The capital of Australia is Canberra.",
        "what is australia's capital" to "The capital of Australia is Canberra.",
        "australia capital city" to "The capital of Australia is Canberra.",

        // 59. Capital of USA
        "capital of usa" to "The capital of the United States is Washington, D.C.",
        "what is usa's capital" to "The capital of the United States is Washington, D.C.",
        "usa capital city" to "The capital of the United States is Washington, D.C.",

        // 60. tallest animal
        "tallest animal" to "The giraffe is the tallest animal on Earth.",
        "which is tallest animal" to "The giraffe is the tallest animal on Earth.",
        "world’s tallest animal" to "The giraffe is the tallest animal on Earth.",

        // 61. Largest volcano
        "largest volcano in the world" to "Mauna Loa in Hawaii is the largest volcano in the world.",
        "which is largest volcano" to "Mauna Loa in Hawaii is the largest volcano in the world.",
        "world's biggest volcano" to "Mauna Loa in Hawaii is the largest volcano in the world.",

        // 62. Longest river in USA
        "longest river in usa" to "The Missouri River is the longest river in the United States.",
        "usa's longest river" to "The Missouri River is the longest river in the United States.",
        "which is longest river in usa" to "The Missouri River is the longest river in the United States.",

        // 63. First man to climb Mount Everest
        "first man to climb mount everest" to "Sir Edmund Hillary and Tenzing Norgay were the first to climb Mount Everest.",
        "who first climbed mount everest" to "Sir Edmund Hillary and Tenzing Norgay were the first to climb Mount Everest.",
        "mount everest first climbers" to "Sir Edmund Hillary and Tenzing Norgay were the first to climb Mount Everest.",

        // 64. Who invented telephone
        "who invented telephone" to "Alexander Graham Bell invented the telephone.",
        "telephone inventor" to "Alexander Graham Bell invented the telephone.",
        "who is the inventor of telephone" to "Alexander Graham Bell invented the telephone.",

        // 65. Smallest planet in solar system
        "smallest planet in solar system" to "Mercury is the smallest planet in our solar system.",
        "which is smallest planet" to "Mercury is the smallest planet in our solar system.",
        "smallest planet" to "Mercury is the smallest planet in our solar system.",

        // 66. Distance from earth to moon
        "distance from earth to moon" to "The average distance from Earth to the Moon is about 384,400 kilometers.",
        "how far is moon from earth" to "The average distance from Earth to the Moon is about 384,400 kilometers.",
        "earth to moon distance" to "The average distance from Earth to the Moon is about 384,400 kilometers.",

        // 67. Light speed in vacuum
        "speed of light in vacuum" to "The speed of light in vacuum is approximately 299,792 kilometers per second.",
        "how fast is light" to "The speed of light in vacuum is approximately 299,792 kilometers per second.",
        "light speed" to "The speed of light in vacuum is approximately 299,792 kilometers per second.",

        // 68. Largest city in the world
        "largest city in the world" to "Tokyo is the largest city in the world by population.",
        "which is world's largest city" to "Tokyo is the largest city in the world by population.",
        "world's biggest city" to "Tokyo is the largest city in the world by population.",

        // 69. First woman in space
        "first woman in space" to "Valentina Tereshkova was the first woman in space.",
        "who was first female astronaut" to "Valentina Tereshkova was the first woman in space.",
        "woman space explorer first" to "Valentina Tereshkova was the first woman in space.",

        // 70. Capital of France
        "capital of france" to "The capital of France is Paris.",
        "what is france capital" to "The capital of France is Paris.",
        "france capital city" to "The capital of France is Paris.",

        // 71. Who painted Mona Lisa
        "who painted mona lisa" to "The Mona Lisa was painted by Leonardo da Vinci.",
        "mona lisa artist" to "The Mona Lisa was painted by Leonardo da Vinci.",
        "who is artist of mona lisa" to "The Mona Lisa was painted by Leonardo da Vinci.",

        // 72. Number of planets in solar system
        "how many planets in solar system" to "There are eight planets in the solar system.",
        "number of planets in solar system" to "There are eight planets in the solar system.",
        "total planets" to "There are eight planets in the solar system.",

        // 73. Human heart beats per minute
        "average human heart beats per minute" to "The average human heart beats about 72 times per minute.",
        "heart beats per minute" to "The average human heart beats about 72 times per minute.",
        "how many heartbeats per minute" to "The average human heart beats about 72 times per minute.",

        // 74. Chemical element for oxygen
        "chemical element for oxygen" to "The chemical symbol for oxygen is O.",
        "oxygen element symbol" to "The chemical symbol for oxygen is O.",
        "what is oxygen symbol" to "The chemical symbol for oxygen is O.",

        // 75. Largest country in Africa
        "largest country in africa" to "Algeria is the largest country in Africa by area.",
        "which is largest african country" to "Algeria is the largest country in Africa by area.",
        "biggest country in africa" to "Algeria is the largest country in Africa by area.",

        // 76. Most visited country in the world
        "most visited country" to "France is the most visited country in the world.",
        "which country has most tourists" to "France is the most visited country in the world.",
        "top tourist destination country" to "France is the most visited country in the world.",

        // 77. Longest railway in the world
        "longest railway in the world" to "The Trans-Siberian Railway in Russia is the longest railway in the world.",
        "which is longest railway" to "The Trans-Siberian Railway in Russia is the longest railway in the world.",
        "world's longest train route" to "The Trans-Siberian Railway in Russia is the longest railway in the world.",

        // 78. Most populous country
        "most populous country" to "China is the most populous country in the world.",
        "which country has highest population" to "China is the most populous country in the world.",
        "country with largest population" to "China is the most populous country in the world.",

        // 79. National flower of USA
        "national flower of usa" to "The national flower of the USA is the rose.",
        "usa national flower" to "The national flower of the USA is the rose.",
        "which is flower of usa" to "The national flower of the USA is the rose.",

        // 80. First artificial satellite
        "first artificial satellite" to "Sputnik 1 was the first artificial satellite sent to space.",
        "which was first satellite" to "Sputnik 1 was the first artificial satellite sent to space.",
        "who launched first satellite" to "Sputnik 1 was the first artificial satellite sent to space.",

        // 81. Largest waterfall by volume
        "largest waterfall by volume" to "Inga Falls in Congo is the largest waterfall by volume.",
        "which is biggest waterfall by water volume" to "Inga Falls in Congo is the largest waterfall by volume.",
        "waterfall with most water" to "Inga Falls in Congo is the largest waterfall by volume.",

        // 82. Lightest element
        "lightest element" to "The lightest element is hydrogen.",
        "which is the lightest element" to "The lightest element is hydrogen.",
        "element with lowest atomic weight" to "The lightest element is hydrogen.",

        // 83. Fastest marine animal
        "fastest marine animal" to "The sailfish is the fastest marine animal.",
        "which is fastest fish" to "The sailfish is the fastest marine animal.",
        "fastest animal in ocean" to "The sailfish is the fastest marine animal.",

        // 84. Longest reigning monarch
        "longest reigning monarch" to "Queen Elizabeth II was the longest reigning monarch of the UK.",
        "who was longest monarch" to "Queen Elizabeth II was the longest reigning monarch of the UK.",
        "record for longest reign monarch" to "Queen Elizabeth II was the longest reigning monarch of the UK.",

        // 85. Chemical element atomic number 1
        "chemical element with atomic number 1" to "Hydrogen has atomic number 1.",
        "which element is number 1" to "Hydrogen has atomic number 1.",
        "atom number 1 element" to "Hydrogen has atomic number 1.",

        // 86. National animal of Australia
        "national animal of australia" to "The red kangaroo is the national animal of Australia.",
        "australia national animal" to "The red kangaroo is the national animal of Australia.",
        "which is australia's national animal" to "The red kangaroo is the national animal of Australia.",

        // 87. National bird of USA
        "national bird of usa" to "The bald eagle is the national bird of the USA.",
        "usa national bird" to "The bald eagle is the national bird of the USA.",
        "which is bird of usa" to "The bald eagle is the national bird of the USA.",

        // 88. Largest lake in the world
        "largest lake in the world" to "The Caspian Sea is the largest lake in the world.",
        "which is biggest lake" to "The Caspian Sea is the largest lake in the world.",
        "world's largest lake" to "The Caspian Sea is the largest lake in the world.",

        // 89. Strongest acid
        "strongest acid" to "Fluoroantimonic acid is the strongest acid known.",
        "which is strongest acid" to "Fluoroantimonic acid is the strongest acid known.",
        "most powerful acid" to "Fluoroantimonic acid is the strongest acid known.",

        // 90. Highest mountain in Africa
        "highest mountain in africa" to "Mount Kilimanjaro is the highest mountain in Africa.",
        "which is tallest mountain in africa" to "Mount Kilimanjaro is the highest mountain in Africa.",
        "africa's highest peak" to "Mount Kilimanjaro is the highest mountain in Africa.",

        // 91. National flower of Japan
        "national flower of japan" to "The cherry blossom is the national flower of Japan.",
        "japan national flower" to "The cherry blossom is the national flower of Japan.",
        "which is japan’s flower" to "The cherry blossom is the national flower of Japan.",

        // 92. Largest coral reef system
        "largest coral reef system" to "The Great Barrier Reef in Australia is the largest coral reef system.",
        "which is biggest coral reef" to "The Great Barrier Reef in Australia is the largest coral reef system.",
        "world’s largest coral reef" to "The Great Barrier Reef in Australia is the largest coral reef system.",

        // 93. Number of continents
        "how many continents" to "There are seven continents in the world.",
        "number of continents" to "There are seven continents in the world.",
        "total continents on earth" to "There are seven continents in the world.",

        // 94. Largest moon of Jupiter
        "largest moon of jupiter" to "Ganymede is the largest moon of Jupiter.",
        "which is biggest jupiter moon" to "Ganymede is the largest moon of Jupiter.",
        "jupiter’s largest moon" to "Ganymede is the largest moon of Jupiter.",

        // 95. Light bulb inventor
        "who invented light bulb" to "Thomas Edison is credited with inventing the light bulb.",
        "light bulb invented by whom" to "Thomas Edison is credited with inventing the light bulb.",
        "inventor of light bulb" to "Thomas Edison is credited with inventing the light bulb.",

        // 96. World’s largest desert (cold desert)
        "largest cold desert" to "Antarctica is the largest cold desert in the world.",
        "which is biggest cold desert" to "Antarctica is the largest cold desert in the world.",
        "world's largest ice desert" to "Antarctica is the largest cold desert in the world.",

        // 97. National fruit of India
        "national fruit of india" to "The mango is the national fruit of India.",
        "india national fruit" to "The mango is the national fruit of India.",
        "which is india’s national fruit" to "The mango is the national fruit of India.",

        // 98. Fastest land mammal
        "fastest land mammal" to "The cheetah is the fastest land mammal.",
        "which is fastest land mammal" to "The cheetah is the fastest land mammal.",
        "fastest mammal on land" to "The cheetah is the fastest land mammal.",

        // 99. Largest fish
        "largest fish" to "The whale shark is the largest fish in the world.",
        "biggest fish" to "The whale shark is the largest fish in the world.",
        "which is biggest fish" to "The whale shark is the largest fish in the world.",

        // 100. Who invented internet
        "who invented internet" to "The internet was invented by multiple people, but Tim Berners-Lee created the World Wide Web.",
        "internet inventor" to "The internet was invented by multiple people, but Tim Berners-Lee created the World Wide Web.",
        "who created internet" to "The internet was invented by multiple people, but Tim Berners-Lee created the World Wide Web.",

                    // Lok Sabha seats
                    "total lok sabha seats" to "India has 543 elected Lok Sabha seats.",
                    "how many lok sabha seats" to "There are 543 elected seats in the Lok Sabha.",
                    "number of lok sabha seats" to "India's Lok Sabha consists of 543 elected seats.",

                    // Number of states and UTs
                    "number of states in india" to "India has 28 states.",
                    "how many states in india" to "There are 28 states in India.",
                    "states count in india" to "The total number of states in India is 28.",
                    "number of union territories in india" to "India has 8 union territories.",
                    "how many union territories in india" to "There are 8 union territories in India.",
                    "union territories count" to "The total number of union territories in India is 8.",

                    // States and their capitals (subset for example, add all as needed)
                    "capital of andhra pradesh" to "The capital of Andhra Pradesh is Amaravati.",
                    "andhra pradesh capital" to "Amaravati is the capital of Andhra Pradesh.",
                    "what is capital of andhra pradesh" to "The capital of Andhra Pradesh is Amaravati.",

                    "capital of arunachal pradesh" to "The capital of Arunachal Pradesh is Itanagar.",
                    "arunachal pradesh capital" to "Itanagar is the capital of Arunachal Pradesh.",
                    "what is capital of arunachal pradesh" to "The capital of Arunachal Pradesh is Itanagar.",

                    "capital of assam" to "The capital of Assam is Dispur.",
                    "assam capital" to "Dispur is the capital of Assam.",
                    "what is capital of assam" to "The capital of Assam is Dispur.",

                    "capital of bihar" to "The capital of Bihar is Patna.",
                    "bihar capital" to "Patna is the capital of Bihar.",
                    "what is capital of bihar" to "The capital of Bihar is Patna.",

                    // Number of districts
                    "number of districts in india" to "India has 776 districts as of 2024.",
                    "how many districts in india" to "There are 776 districts in India.",
                    "total districts in india" to "The total number of districts in India is 776.",

                    // Number of languages
                    "number of languages in india" to "India has 22 officially recognized languages.",
                    "how many languages in india" to "There are 22 scheduled languages in India.",
                    "official languages of india count" to "India officially recognizes 22 scheduled languages.",

                    // Major castes
                    "major castes in india" to "Major caste groups in India include Brahmins, Kshatriyas, Vaishyas, and Shudras.",
                    "what are major castes in india" to "India's major castes include Brahmins, Kshatriyas, Vaishyas, and Shudras.",
                    "major caste groups in india" to "The four major caste groups in India are Brahmins, Kshatriyas, Vaishyas, and Shudras.",

                    // Minor castes and tribes
                    "minor castes in india" to "India has numerous minor castes and Scheduled Tribes including Bhils, Gonds, and Santals.",
                    "what are minor castes in india" to "Minor castes and Scheduled Tribes in India include Bhils, Gonds, and Santals.",
                    "tribes and minor castes in india" to "Minor castes and tribes in India include Bhils, Gonds, Santals, and others.",

                    // Union territories and capitals (subset)
                    "capital of delhi" to "The capital of Delhi is New Delhi.",
                    "delhi capital" to "New Delhi is the capital of Delhi.",
                    "what is capital of delhi" to "The capital of Delhi is New Delhi.",

                    "capital of puducherry" to "The capital of Puducherry is Puducherry.",
                    "puducherry capital" to "Puducherry city is the capital of Puducherry.",
                    "what is capital of puducherry" to "The capital of Puducherry is Puducherry.",

                    // Example language questions
                    "most spoken language in india" to "Hindi is the most spoken language in India.",
                    "second most spoken language in india" to "Bengali is the second most spoken language in India.",
                    "popular languages in india" to "Popular languages in India include Hindi, Bengali, Telugu, Tamil, Marathi, and Urdu.",

                    // Example district question
                    "largest district in india" to "The largest district by area in India is Leh in Ladakh.",
                    "most populous district in india" to "North 24 Parganas in West Bengal is the most populous district in India.",
                    "which is biggest district in india" to "Leh district in Ladakh is the largest by area.",

                    // Seats in Rajya Sabha
                    "total rajya sabha seats" to "Rajya Sabha has 245 seats in total.",
                    "how many seats in rajya sabha" to "There are 245 seats in the Rajya Sabha.",
                    "number of members in rajya sabha" to "Rajya Sabha consists of 245 members.",

                    // Total population
                    "population of india" to "India's population is approximately 1.4 billion as of 2025.",
                    "how many people in india" to "India has about 1.4 billion people.",
                    "india population count" to "The estimated population of India in 2025 is 1.4 billion.",

                    // More capitals (add as needed)
                    "capital of karnataka" to "The capital of Karnataka is Bengaluru.",
                    "karnataka capital" to "Bengaluru is the capital of Karnataka.",
                    "what is capital of karnataka" to "The capital of Karnataka is Bengaluru.",

                    "capital of maharashtra" to "The capital of Maharashtra is Mumbai.",
                    "maharashtra capital" to "Mumbai is the capital of Maharashtra.",
                    "what is capital of maharashtra" to "The capital of Maharashtra is Mumbai.",

                    "capital of tamil nadu" to "The capital of Tamil Nadu is Chennai.",
                    "tamil nadu capital" to "Chennai is the capital of Tamil Nadu.",
                    "what is capital of tamil nadu" to "The capital of Tamil Nadu is Chennai.",

                    // Political party seats (example)
                    "nda seats in lok sabha" to "The NDA currently holds 293 Lok Sabha seats.",
                    "number of nda seats" to "NDA has 293 seats in the Lok Sabha.",
                    "lok sabha seats held by nda" to "NDA holds 293 seats in the Lok Sabha.",

                    "congress seats in lok sabha" to "The Congress party holds about 52 Lok Sabha seats.",
                    "number of congress seats" to "Congress has 52 seats in the Lok Sabha.",
                    "lok sabha seats held by congress" to "Congress holds 52 seats in the Lok Sabha.",
        "capital of andhra pradesh" to "The capital of Andhra Pradesh is Amaravati.",
        "andhra pradesh capital" to "Amaravati is the capital of Andhra Pradesh.",
        "what is capital of andhra pradesh" to "The capital of Andhra Pradesh is Amaravati.",

        "capital of arunachal pradesh" to "The capital of Arunachal Pradesh is Itanagar.",
        "arunachal pradesh capital" to "Itanagar is the capital of Arunachal Pradesh.",
        "what is capital of arunachal pradesh" to "The capital of Arunachal Pradesh is Itanagar.",

        "capital of assam" to "The capital of Assam is Dispur.",
        "assam capital" to "Dispur is the capital of Assam.",
        "what is capital of assam" to "The capital of Assam is Dispur.",

        "capital of bihar" to "The capital of Bihar is Patna.",
        "bihar capital" to "Patna is the capital of Bihar.",
        "what is capital of bihar" to "The capital of Bihar is Patna.",

        "capital of chhattisgarh" to "The capital of Chhattisgarh is Raipur.",
        "chhattisgarh capital" to "Raipur is the capital of Chhattisgarh.",
        "what is capital of chhattisgarh" to "The capital of Chhattisgarh is Raipur.",

        "capital of goa" to "The capital of Goa is Panaji.",
        "goa capital" to "Panaji is the capital of Goa.",
        "what is capital of goa" to "The capital of Goa is Panaji.",

        "who is your daddy" to "aaaahnnnand!!!!   is my daddy ",

        "capital of gujarat" to "The capital of Gujarat is Gandhinagar.",
        "gujarat capital" to "Gandhinagar is the capital of Gujarat.",
        "what is capital of gujarat" to "The capital of Gujarat is Gandhinagar.",

        "capital of haryana" to "The capital of Haryana is Chandigarh.",
        "haryana capital" to "Chandigarh is the capital of Haryana.",
        "what is capital of haryana" to "The capital of Haryana is Chandigarh.",

        "capital of himachal pradesh" to "The capital of Himachal Pradesh is Shimla.",
        "himachal pradesh capital" to "Shimla is the capital of Himachal Pradesh.",
        "what is capital of himachal pradesh" to "The capital of Himachal Pradesh is Shimla.",

        "capital of jharkhand" to "The capital of Jharkhand is Ranchi.",
        "jharkhand capital" to "Ranchi is the capital of Jharkhand.",
        "what is capital of jharkhand" to "The capital of Jharkhand is Ranchi.",

        "capital of karnataka" to "The capital of Karnataka is Bengaluru.",
        "karnataka capital" to "Bengaluru is the capital of Karnataka.",
        "what is capital of karnataka" to "The capital of Karnataka is Bengaluru.",

        "capital of kerala" to "The capital of Kerala is Thiruvananthapuram.",
        "kerala capital" to "Thiruvananthapuram is the capital of Kerala.",
        "what is capital of kerala" to "The capital of Kerala is Thiruvananthapuram.",

        "capital of madhya pradesh" to "The capital of Madhya Pradesh is Bhopal.",
        "madhya pradesh capital" to "Bhopal is the capital of Madhya Pradesh.",
        "what is capital of madhya pradesh" to "The capital of Madhya Pradesh is Bhopal.",

        "capital of maharashtra" to "The capital of Maharashtra is Mumbai.",
        "maharashtra capital" to "Mumbai is the capital of Maharashtra.",
        "what is capital of maharashtra" to "The capital of Maharashtra is Mumbai.",

        "capital of manipur" to "The capital of Manipur is Imphal.",
        "manipur capital" to "Imphal is the capital of Manipur.",
        "what is capital of manipur" to "The capital of Manipur is Imphal.",

        "capital of meghalaya" to "The capital of Meghalaya is Shillong.",
        "meghalaya capital" to "Shillong is the capital of Meghalaya.",
        "what is capital of meghalaya" to "The capital of Meghalaya is Shillong.",

        "capital of mizoram" to "The capital of Mizoram is Aizawl.",
        "mizoram capital" to "Aizawl is the capital of Mizoram.",
        "what is capital of mizoram" to "The capital of Mizoram is Aizawl.",

        "capital of nagaland" to "The capital of Nagaland is Kohima.",
        "nagaland capital" to "Kohima is the capital of Nagaland.",
        "what is capital of nagaland" to "The capital of Nagaland is Kohima.",

        "capital of odisha" to "The capital of Odisha is Bhubaneswar.",
        "odisha capital" to "Bhubaneswar is the capital of Odisha.",
        "what is capital of odisha" to "The capital of Odisha is Bhubaneswar.",

        "capital of punjab" to "The capital of Punjab is Chandigarh.",
        "punjab capital" to "Chandigarh is the capital of Punjab.",
        "what is capital of punjab" to "The capital of Punjab is Chandigarh.",

        "capital of rajasthan" to "The capital of Rajasthan is Jaipur.",
        "rajasthan capital" to "Jaipur is the capital of Rajasthan.",
        "what is capital of rajasthan" to "The capital of Rajasthan is Jaipur.",

        "capital of sikkim" to "The capital of Sikkim is Gangtok.",
        "sikkim capital" to "Gangtok is the capital of Sikkim.",
        "what is capital of sikkim" to "The capital of Sikkim is Gangtok.",

        "capital of tamil nadu" to "The capital of Tamil Nadu is Chennai.",
        "tamil nadu capital" to "Chennai is the capital of Tamil Nadu.",
        "what is capital of tamil nadu" to "The capital of Tamil Nadu is Chennai.",

        "capital of telangana" to "The capital of Telangana is Hyderabad.",
        "telangana capital" to "Hyderabad is the capital of Telangana.",
        "what is capital of telangana" to "The capital of Telangana is Hyderabad.",

        "capital of tripura" to "The capital of Tripura is Agartala.",
        "tripura capital" to "Agartala is the capital of Tripura.",
        "what is capital of tripura" to "The capital of Tripura is Agartala.",

        "capital of uttarakhand" to "The capital of Uttarakhand is Dehradun.",
        "uttarakhand capital" to "Dehradun is the capital of Uttarakhand.",
        "what is capital of uttarakhand" to "The capital of Uttarakhand is Dehradun.",

        "capital of uttar pradesh" to "The capital of Uttar Pradesh is Lucknow.",
        "uttar pradesh capital" to "Lucknow is the capital of Uttar Pradesh.",
        "what is capital of uttar pradesh" to "The capital of Uttar Pradesh is Lucknow.",

        "capital of west bengal" to "The capital of West Bengal is Kolkata.",
        "west bengal capital" to "Kolkata is the capital of West Bengal.",
        "what is capital of west bengal" to "The capital of West Bengal is Kolkata.",

        // Indian economy and GDP facts
        "indian economy" to "India is the world's fifth-largest economy by nominal GDP and the third-largest by purchasing power parity (PPP) as of 2025.",
        "economy of india" to "India's economy is characterized by agriculture, manufacturing, and services sectors, with services contributing the most to GDP.",
        "overview of indian economy" to "India's economy is diverse and rapidly growing, with key industries including IT services, pharmaceuticals, textiles, and agriculture.",

        "india gdp 2025" to "India's nominal GDP in 2025 is approximately 3.7 trillion US dollars.",
        "gdp of india 2025" to "India's GDP is around 3.7 trillion USD in 2025 making it a major emerging economy.",
        "india gross domestic product" to "India's gross domestic product in 2025 is estimated at 3.7 trillion US dollars.",

        "india gdp growth rate" to "India's GDP growth rate is projected to be around 6.5% in 2025.",
        "growth rate of india economy" to "India's economy is expected to grow approximately 6.5% in 2025.",
        "india economic growth" to "India is among the fastest-growing major economies with a 6.5% projected growth rate in 2025.",

                )



            }




