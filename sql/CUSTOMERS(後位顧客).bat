@echo off
set /p x=please enter the password:
	rem start inserting data
	redis-cli -a %x% hset cus:001 CUS_NO 001
	redis-cli -a %x% hset cus:001 CUS_NAME Shu_I_Lu 
	redis-cli -a %x% hset cus:001 CUS_PHONE 022338956
	redis-cli -a %x% hset cus:001 CUS_PEOPLE 2
	redis-cli -a %x% hmset cus:002 CUS_NO 002 CUS_NAME Pete_Dale CUS_PHONE 022338956 CUS_PEOPLE 5
	redis-cli -a %x% hmset cus:003 CUS_NO 003 CUS_NAME Noah_Witton CUS_PHONE 032377537 CUS_PEOPLE 3 
	redis-cli -a %x% hmset cus:004 CUS_NO 004 CUS_NAME Dan_Adams CUS_PHONE 022338956 CUS_PEOPLE 4
	redis-cli -a %x% hmset cus:005 CUS_NO 005 CUS_NAME Kelly_Vives CUS_PHONE 049682174 CUS_PEOPLE 1
	redis-cli -a %x% hmset cus:006 CUS_NO 006 CUS_NAME Megan_Cecil CUS_PHONE 022338956 CUS_PEOPLE 1
	redis-cli -a %x% hmset cus:007 CUS_NO 007 CUS_NAME Sarita_Gardner CUS_PHONE 022338956 CUS_PEOPLE 1
	redis-cli -a %x% hmset cus:008 CUS_NO 008 CUS_NAME Lily_Ford CUS_PHONE 022338956 CUS_PEOPLE 2
	redis-cli -a %x% hmset cus:009 CUS_NO 009 CUS_NAME Mark_Breslin CUS_PHONE 022567795 CUS_PEOPLE 2 
	redis-cli -a %x% hmset cus:010 CUS_NO 010 CUS_NAME Jonathan_Middleton CUS_PHONE 0915963265 CUS_PEOPLE 3
	redis-cli -a %x% hmset cus:011 CUS_NO 011 CUS_NAME Jeremy_Paisley CUS_PHONE 022338956 CUS_PEOPLE 2
	redis-cli -a %x% hmset cus:012 CUS_NO 012 CUS_NAME Jocelyn_Harmon CUS_PHONE 022338956 CUS_PEOPLE 4
	redis-cli -a %x% hmset cus:013 CUS_NO 013 CUS_NAME Brianna_Murphy CUS_PHONE 0927330258 CUS_PEOPLE 5
	redis-cli -a %x% hmset cus:014 CUS_NO 014 CUS_NAME Olivia_Hawkins CUS_PHONE 022338956 CUS_PEOPLE 6	
	redis-cli -a %x% hmset cus:015 CUS_NO 015 CUS_NAME Dalton_Bledel CUS_PHONE 022338956 CUS_PEOPLE 1 
	redis-cli -a %x% hmset cus:016 CUS_NO 016 CUS_NAME Andy_Heigl CUS_PHONE 022338956 CUS_PEOPLE 5
	redis-cli -a %x% hmset cus:017 CUS_NO 017 CUS_NAME Louis_Lewis CUS_PHONE 0937495511 CUS_PEOPLE 5
	redis-cli -a %x% hmset cus:018 CUS_NO 018 CUS_NAME Emily_Smith CUS_PHONE 022338956 CUS_PEOPLE 5
	redis-cli -a %x% hmset cus:019 CUS_NO 019 CUS_NAME Alexia_Hampden CUS_PHONE 022338956 CUS_PEOPLE 5
	redis-cli -a %x% hmset cus:020 CUS_NO 020 CUS_NAME Alexia_Hampden CUS_PHONE 022338956 CUS_PEOPLE 5
	redis-cli -a %x% hmset cus:021 CUS_NO 021 CUS_NAME Lucas_Buffett CUS_PHONE 022338956 CUS_PEOPLE 5 
	redis-cli -a %x% hmset cus:022 CUS_NO 022 CUS_NAME Tony_Cassidy	CUS_PHONE 0937564429 CUS_PEOPLE 5
	redis-cli -a %x% hmset cus:023 CUS_NO 023 CUS_NAME Anthony_Whalen CUS_PHONE 022338956 CUS_PEOPLE 5
	redis-cli -a %x% hmset cus:024 CUS_NO 024 CUS_NAME Sophia_Ford CUS_PHONE 022338956 CUS_PEOPLE 5
	redis-cli -a %x% hmset cus:025 CUS_NO 025 CUS_NAME Victoria_Breslin CUS_PHONE 022338956 CUS_PEOPLE 5

echo inserting finish!


pause