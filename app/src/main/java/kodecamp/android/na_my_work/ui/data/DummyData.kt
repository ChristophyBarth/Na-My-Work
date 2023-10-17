package kodecamp.android.na_my_work.ui.data

import kodecamp.android.na_my_work.ui.model.UserEntity
import kodecamp.android.na_my_work.ui.model.WorkExperience

class DummyData {
    fun fakeProfiles(): List<UserEntity> {
        return mutableListOf(
            UserEntity(
                userId = 1,
                fullName = "John Smith",
                email = "john.smith@example.com",
                password = "password123",
                phoneNumber = "123-456-7890",
                dateOfBirth = "1990-05-15",
                profilePicture = null,
                countryOfResidence = "USA",
                state = "California",
                cityOfResidence = "Los Angeles",
                title = "Senior Software Engineer",
                bio = "John Smith is a highly skilled Software Engineer based in Los Angeles, California. With over a decade of experience in the technology industry, he has played a pivotal role in developing web applications that have transformed businesses.",
                twoStepVerification = true,
                rating = 4.5,
                ratingCount = 23,
                link = "https://linkedin.com/johnsmith",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Technology",
                experience = mutableListOf(
                    WorkExperience(
                        "ABC Inc.",
                        "Software Developer",
                        1115817600000,
                        1146667200000,
                        false,
                        "Developed web applications"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),
            UserEntity(
                userId = 2,
                fullName = "Alice Johnson",
                email = "alice.johnson@example.com",
                password = "pass123",
                phoneNumber = "987-654-3210",
                dateOfBirth = "1985-08-20",
                profilePicture = null,
                countryOfResidence = "Canada",
                state = "Ontario",
                cityOfResidence = "Toronto",
                title = "Contemporary Artist",
                bio = "Alice Johnson is an accomplished artist known for her outstanding work in the field of Arts and Crafts. Hailing from Toronto, Ontario, she specializes in creating exquisite and meaningful artworks that inspire and captivate. Alice's paintings are a reflection of her creativity and unique artistic vision.",
                twoStepVerification = false,
                rating = 3.8,
                ratingCount = 13,
                link = "https://linkedin.com/alicejohnson",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Arts & Crafts",
                experience = mutableListOf(
                    WorkExperience(
                        "Art Studio",
                        "Painter",
                        1146811200000,
                        1178001600000,
                        false,
                        "Created beautiful artworks"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 3,
                fullName = "David Lee",
                email = "david.lee@example.com",
                password = "securePwd",
                phoneNumber = "555-123-4567",
                dateOfBirth = "1995-03-10",
                profilePicture = null,
                countryOfResidence = "UK",
                state = "England",
                cityOfResidence = "London",
                title = "Civil Engineer",
                bio = "David Lee, a Civil Engineer based in Edinburgh, Scotland, possesses a deep passion for building a sustainable future. He has a proven track record of managing construction projects with precision and innovation. David's work focuses on designing resilient and environmentally friendly structures that stand the test of time.",
                twoStepVerification = true,
                rating = 4.2,
                ratingCount = 37,
                link = "https://linkedin.com/davidlee",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Engineering & Construction",
                experience = mutableListOf(
                    WorkExperience(
                        "BuildIt Inc.",
                        "Structural Engineer",
                        1178212800000,
                        1209513600000,
                        false,
                        "Designed strong bridges"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 4,
                fullName = "Christine Brown",
                email = "christine.brown@example.com",
                password = "password456",
                phoneNumber = "777-888-9999",
                dateOfBirth = "1988-12-05",
                profilePicture = null,
                countryOfResidence = "USA",
                state = "New York",
                cityOfResidence = "New York City",
                title = "Registered Nurse",
                bio = "Christine Brown is a dedicated Healthcare Professional located in New York City. Her journey as a nurse has been marked by compassionate care and a commitment to patient well-being. She brings her expertise to City Hospital, where she plays a vital role in providing essential medical assistance.",
                twoStepVerification = false,
                rating = 4.0,
                ratingCount = 82,
                link = "https://linkedin.com/christinebrown",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Healthcare Services",
                experience = mutableListOf(
                    WorkExperience(
                        "City Hospital",
                        "Nurse",
                        1209753600000,
                        1242182400000,
                        false,
                        "Provided patient care"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 5,
                fullName = "Michael Chang",
                email = "michael.chang@example.com",
                password = "mike1234",
                phoneNumber = "123-555-7890",
                dateOfBirth = "1993-07-25",
                profilePicture = null,
                countryOfResidence = "USA",
                state = "Texas",
                cityOfResidence = "Houston",
                title = "Android Developer",
                bio = "Michael Chang, a seasoned Software Developer in Houston, Texas, is a driving force in the world of technology. With a knack for creating cutting-edge mobile apps, he has made a significant impact on user experiences. Michael is a proficient coder in Kotlin and is known for delivering high-quality software solutions.",
                twoStepVerification = true,
                rating = 4.6,
                ratingCount = 29,
                link = "https://linkedin.com/michaelchang",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Technology",
                experience = mutableListOf(
                    WorkExperience(
                        "Tech Innovators",
                        "Frontend Developer",
                        1242518400000,
                        0,
                        true,
                        "Developed responsive web interfaces"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 6,
                fullName = "Sarah Davis",
                email = "sarah.davis@example.com",
                password = "pass456",
                phoneNumber = "555-987-1234",
                dateOfBirth = "1990-04-17",
                profilePicture = null,
                countryOfResidence = "Canada",
                state = "British Columbia",
                cityOfResidence = "Vancouver",
                title = "Abstract Painter",
                bio = "Sarah Davis, a talented painter from Vancouver, British Columbia, is recognized for her expressive and captivating art. She specializes in abstract art, where her creations delve into emotions and imagination. Sarah's studio is a haven for creativity and showcases her ability to convey stories through her paintings.",
                twoStepVerification = false,
                rating = 3.9,
                ratingCount = 32,
                link = "https://linkedin.com/sarahdavis",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Arts & Crafts",
                experience = mutableListOf(
                    WorkExperience(
                        "Creative Arts Studio",
                        "Abstract Artist",
                        1273948800000,
                        1306752000000,
                        false,
                        "Created unique abstract paintings"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 7,
                fullName = "James Miller",
                email = "james.miller@example.com",
                password = "secure123",
                phoneNumber = "888-444-2222",
                dateOfBirth = "1991-06-30",
                profilePicture = null,
                countryOfResidence = "UK",
                state = "Scotland",
                cityOfResidence = "Edinburgh",
                title = "Structural Engineer",
                bio = "James Miller, a Civil Engineer based in Manchester, England, is a visionary leader in the field of structural engineering. His role as a project manager at Structura Corp. has seen him successfully oversee numerous construction projects, delivering results that inspire confidence. James is dedicated to building a better and more sustainable world through innovative engineering solutions.",
                twoStepVerification = true,
                rating = 4.4,
                ratingCount = 38,
                link = "https://linkedin.com/jamesmiller",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Engineering & Construction",
                experience = mutableListOf(
                    WorkExperience(
                        "Structura Corp.",
                        "Project Manager",
                        1306891200000,
                        1338371200000,
                        false,
                        "Managed construction projects"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 8,
                fullName = "Linda Wilson",
                email = "linda.wilson@example.com",
                password = "password789",
                phoneNumber = "333-777-9999",
                dateOfBirth = "1987-09-15",
                profilePicture = null,
                countryOfResidence = "USA",
                state = "California",
                cityOfResidence = "San Francisco",
                title = "General Practitioner",
                bio = "Linda Wilson is a respected Medical Doctor in San Francisco, California, renowned for her commitment to patient care and her contributions to the field of healthcare. Her work as a General Practitioner at City Clinic allows her to make a meaningful impact on the well-being of her patients. Linda's empathetic approach and medical expertise are instrumental in healing the community.",
                twoStepVerification = false,
                rating = 4.7,
                ratingCount = 53,
                link = "https://linkedin.com/lindawilson",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Healthcare Services",
                experience = mutableListOf(
                    WorkExperience(
                        "City Clinic",
                        "General Practitioner",
                        1338732800000,
                        0,
                        true,
                        "Provided medical care to patients"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),
            UserEntity(
                userId = 9,
                fullName = "William Turner",
                email = "william.turner@example.com",
                password = "will123",
                phoneNumber = "444-888-7777",
                dateOfBirth = "1991-09-20",
                profilePicture = null,
                countryOfResidence = "UK",
                state = "Scotland",
                cityOfResidence = "Glasgow",
                title = "Senior Structural Engineer",
                bio = "William Turner is a visionary Civil Engineer in Glasgow, Scotland, dedicated to creating resilient and sustainable infrastructure. His role as a Senior Structural Engineer at Urban Structures has seen him lead complex construction projects with precision and innovation. William's commitment to engineering excellence and a better future is unwavering.",
                twoStepVerification = true,
                rating = 4.6,
                ratingCount = 12,
                link = "https://linkedin.com/williamturner",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Engineering & Construction",
                experience = mutableListOf(
                    WorkExperience(
                        "Urban Structures",
                        "Senior Structural Engineer",
                        1369878400000,
                        0,
                        true,
                        "Led complex construction projects"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),
            UserEntity(
                userId = 10,
                fullName = "Emily Garcia",
                email = "emily.garcia@example.com",
                password = "emily456",
                phoneNumber = "999-333-7777",
                dateOfBirth = "1992-11-12",
                profilePicture = null,
                countryOfResidence = "Canada",
                state = "Quebec",
                cityOfResidence = "Montreal",
                title = "Portrait Artist",
                bio = "Emily Garcia, a remarkable painter based in Montreal, Quebec, is celebrated for her artistic brilliance in portrait art. Her lifelike portraits are a testament to her artistic finesse and ability to capture the essence of her subjects. Emily's art studio, Art Expressions, is a place where she channels her creativity and produces exquisite masterpieces.",
                twoStepVerification = false,
                rating = 4.1,
                ratingCount = 8,
                link = "https://linkedin.com/emilygarcia",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Arts & Crafts",
                experience = mutableListOf(
                    WorkExperience(
                        "Art Expressions",
                        "Portrait Artist",
                        1401705600000,
                        0,
                        true,
                        "Created lifelike portraits"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 11,
                fullName = "Robert Brown",
                email = "robert.brown@example.com",
                password = "rob1234",
                phoneNumber = "666-222-8888",
                dateOfBirth = "1989-10-03",
                profilePicture = null,
                countryOfResidence = "UK",
                state = "England",
                cityOfResidence = "Manchester",
                title = "Senior Structural Engineer",
                bio = "Robert Brown is a highly accomplished Structural Engineer based in London, England. With a career marked by innovation and precision, he has designed resilient and groundbreaking building structures. Robert's work at BuildTech Solutions has led to architectural marvels that are a testament to his engineering prowess.",
                twoStepVerification = true,
                rating = 4.5,
                ratingCount = 71,
                link = "https://linkedin.com/robertbrown",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Engineering & Construction",
                experience = mutableListOf(
                    WorkExperience(
                        "BuildTech Solutions",
                        "Structural Engineer",
                        1433044800000,
                        1466380800000,
                        false,
                        "Designed innovative building structures"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 12,
                fullName = "Olivia King",
                email = "olivia.king@example.com",
                password = "olivia567",
                phoneNumber = "111-777-3333",
                dateOfBirth = "1986-04-28",
                profilePicture = null,
                countryOfResidence = "USA",
                state = "New York",
                cityOfResidence = "New York City",
                title = "Medical Research Scientist",
                bio = "Olivia King, a dedicated Medical Researcher in New York City, is at the forefront of scientific exploration. Her work at the Health Innovations Lab involves pioneering medical research that has the potential to transform healthcare. Olivia's commitment to pushing the boundaries of medical knowledge and her rigorous scientific approach have earned her recognition in the research community.",
                twoStepVerification = false,
                rating = 4.6,
                ratingCount = 59,
                link = "https://linkedin.com/oliviaking",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Healthcare Services",
                experience = mutableListOf(
                    WorkExperience(
                        "Health Innovations Lab",
                        "Research Scientist",
                        1466601600000,
                        1499760000000,
                        false,
                        "Conducted groundbreaking medical research"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 13,
                fullName = "Sultan Anon",
                email = "anon.sultan@example.com",
                password = "sultan377",
                phoneNumber = "444-111-5555",
                dateOfBirth = "1997-03-22",
                profilePicture = null,
                countryOfResidence = "USA",
                state = "California",
                cityOfResidence = "Los Angeles",
                title = "Android Developer",
                bio = "Sultan Anon is an Android Developer based in Los Angeles, California, known for his prowess in mobile app development. He has a remarkable track record of creating top-rated apps that resonate with users. Sultan's work at Tech Wizards showcases his expertise in Kotlin and his ability to develop cutting-edge software solutions that enhance the digital experience.",
                twoStepVerification = true,
                rating = 4.4,
                ratingCount = 23,
                link = "https://linkedin.com/danieljohnson",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Technology",
                experience = mutableListOf(
                    WorkExperience(
                        "Tech Wizards",
                        "Android Developer",
                        1499875200000,
                        1528041600000,
                        false,
                        "Created top-rated mobile apps"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 14,
                fullName = "Sophia Clark",
                email = "sophia.clark@example.com",
                password = "sophia456",
                phoneNumber = "222-444-8888",
                dateOfBirth = "1988-08-15",
                profilePicture = null,
                countryOfResidence = "Canada",
                state = "Ontario",
                cityOfResidence = "Toronto",
                title = "Digital Illustrator",
                bio = "Sophia Clark is a celebrated Digital Artist in Toronto, Ontario, recognized for her captivating digital illustrations. Her creative prowess and unique artistic vision are evident in every piece she produces. Sophia's work at Digital Creations Studio is a testament to her ability to bring digital art to life, creating visually stunning designs that inspire.",
                twoStepVerification = false,
                rating = 4.2,
                ratingCount = 92,
                link = "https://linkedin.com/sophiaclark",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Arts & Crafts",
                experience = mutableListOf(
                    WorkExperience(
                        "Digital Creations Studio",
                        "Digital Illustrator",
                        1531142400000,
                        1559913600000,
                        false,
                        "Produced stunning digital artworks"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 15,
                fullName = "Matthew White",
                email = "matthew.white@example.com",
                password = "matt1234",
                phoneNumber = "777-222-5555",
                dateOfBirth = "1993-11-05",
                profilePicture = null,
                countryOfResidence = "UK",
                state = "England",
                cityOfResidence = "London",
                title = "Civil Engineer",
                bio = "Matthew White, a Civil Engineer in London, England, is dedicated to engineering a sustainable and resilient world. His career as a civil engineer has been marked by managing complex construction projects and designing infrastructure that stands the test of time. Matthew's work at ConstructWorks is characterized by innovation and a commitment to building a better future.",
                twoStepVerification = true,
                rating = 4.3,
                ratingCount = 33,
                link = "https://linkedin.com/matthewwhite",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Engineering & Construction",
                experience = mutableListOf(
                    WorkExperience(
                        "ConstructWorks",
                        "Civil Engineer",
                        1560672000000,
                        1585142400000,
                        false,
                        "Engineered sustainable infrastructure projects"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 16,
                fullName = "Ava Adams",
                email = "ava.adams@example.com",
                password = "ava123",
                phoneNumber = "333-999-4444",
                dateOfBirth = "1994-06-18",
                profilePicture = null,
                countryOfResidence = "USA",
                state = "Texas",
                cityOfResidence = "Dallas",
                title = "Registered Nurse",
                bio = "Ava Adams is a compassionate Nurse based in Dallas, Texas, renowned for her commitment to patient well-being. Her work as a Registered Nurse at HealWell Hospital reflects her dedication to providing compassionate nursing care. Ava's empathetic approach, clinical expertise, and nurturing presence make her a valued healthcare professional in her community.",
                twoStepVerification = false,
                rating = 4.5,
                ratingCount = 40,
                link = "https://linkedin.com/avaadams",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Healthcare Services",
                experience = mutableListOf(
                    WorkExperience(
                        "HealWell Hospital",
                        "Registered Nurse",
                        1585353600000,
                        1609891200000,
                        false,
                        "Provided compassionate nursing care"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),

            UserEntity(
                userId = 17,
                fullName = "Christophy Barth",
                email = "chris.brth@example.com",
                password = "christ717",
                phoneNumber = "888-111-2222",
                dateOfBirth = "1985-12-14",
                profilePicture = null,
                countryOfResidence = "USA",
                state = "California",
                cityOfResidence = "San Diego",
                title = "Software Developer",
                bio = "Christophy Barth, a Software Engineer in San Diego, California, is dedicated to creating innovative software solutions that shape the digital landscape. His work at CodeCrafters showcases his mastery of coding in Kotlin and his ability to develop software that is both reliable and user-friendly. Christophy is known for his commitment to quality and his drive to keep pushing the boundaries of technology.",
                twoStepVerification = true,
                rating = 4.4,
                ratingCount = 36,
                link = "https://linkedin.com/ethanhall",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Technology",
                experience = mutableListOf(
                    WorkExperience(
                        "CodeCrafters",
                        "Software Developer",
                        1614556800000,
                        1645200000000,
                        false,
                        "Developed innovative software solutions"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),
            UserEntity(
                userId = 18,
                fullName = "Mia Parker",
                email = "mia.parker@example.com",
                password = "mia456",
                phoneNumber = "555-333-1111",
                dateOfBirth = "1990-03-26",
                profilePicture = null,
                countryOfResidence = "Canada",
                state = "British Columbia",
                cityOfResidence = "Vancouver",
                title = "Graphic Designer",
                bio = "Mia Parker, a talented Graphic Designer in Vancouver, British Columbia, is known for her exceptional design skills and a keen eye for aesthetics. Her work at DesignHub has yielded visually stunning designs that leave a lasting impact. Mia believes in the power of graphic design to communicate messages effectively and appreciates the beauty in creativity.",
                twoStepVerification = false,
                rating = 4.3,
                ratingCount = 42,
                link = "https://linkedin.com/miaparker",
                appNotification = null,
                emailNotification = null,
                profileSetupStatus = "Incomplete",
                category = "Arts & Crafts",
                experience = mutableListOf(
                    WorkExperience(
                        "DesignHub",
                        "Graphic Designer",
                        1634150400000,
                        1665686400000,
                        false,
                        "Created visually stunning designs"
                    )
                ),
                softSkills = mutableListOf(),
                photos = mutableListOf()
            ),
        )
    }

    fun currencies(): List<String> {
        return mutableListOf(
            "Nigerian Naira (NGN)",
            "United States Dollar (USD)",
            "Euro (EUR)",
            "Japanese Yen (JPY)",
            "British Pound (GBP)",
            "Canadian Dollar (CAD)",
            "Australian Dollar (AUD)",
            "Swiss Franc (CHF)",
            "Chinese Yuan (CNY)",
            "Indian Rupee (INR)",
            "Brazilian Real (BRL)",
            "South African Rand (ZAR)",
            "Mexican Peso (MXN)",
            "Russian Ruble (RUB)",
            "Saudi Riyal (SAR)",
            "Singapore Dollar (SGD)",
            "South Korean Won (KRW)",
            "New Zealand Dollar (NZD)",
            "United Arab Emirates Dirham (AED)"
        )
    }
}