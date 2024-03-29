<p align="center">
  <img src="https://user-images.githubusercontent.com/62407188/190665632-a4db87a1-92ea-43c7-884b-00cd4b2027e5.png" alt="Мedication Тherapy Мanagement" width="250" height="250"/>
</p>

## Мedication therapy management web app [(Advanced Human-Computer Interaction)](https://www.finki.ukim.mk/en/subject/advanced-human-computer-interaction-0)
#### Idea

Managing medications, including remembering when and how to take them, can be challenging, especially for those juggling many responsibilities and activities. 
However, forgetting to take your medications can have negative, and even life-threatening, consequences.

This can often be a burden for the elderly and cause problems in taking the medicines on time, 
whether they are taken before or after a meal, which medicine in which dose, etc. 

#### Research
A lot of research has been done in this domain, and some solutions have also been developed. 
All proposals and solutions address the problems of users to monitor the parameters of their medicines, 
to have the possibility to change them, to monitor and to be notified about the state of the stock of their medicines.

#### Purpose
The purpose of this drug therapy management application would be to make it easier for users who have regular drug therapies on a daily basis, to track their therapy and 
in addition to serve as a helper who will remind them.

#### Demo
###### Login
In-memory stored credentials which can be used for login:
* username: test
* password: test
###### Medical therapy status refresh
*Medical therapy status* is refreshed every day at noon and set as *incomplete* for the following day.
To review this functionality replace the 
[**cron**](https://github.com/ognjanoskajelena/mtm-app/blob/c8a677275ce47814985c87bf8b58a6aea8607f23/src/main/java/mk/ukim/finki/mtmapp/job/ScheduledTasks.java#L19) 
value with ```0/30 * * * * ?```, and the status will refresh after every 30 seconds.
