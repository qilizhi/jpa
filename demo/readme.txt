Keyword Sample JPQL snippet

And
findByLastnameAndFirstnameˇ­ where x.lastname = ?1 and
x.firstname = ?2
Or
findByLastnameOrFirstnameˇ­ where x.lastname = ?1 or x.firstname
= ?2
Between 
findByStartDateBetween ˇ­ where x.startDate between 1? and ?2
LessThan 
findByAgeLessThan ˇ­ where x.age < ?1
GreaterThan 
findByAgeGreaterThan ˇ­ where x.age > ?1
After 
findByStartDateAfter ˇ­ where x.startDate > ?1
Before 
findByStartDateBefore ˇ­ where x.startDate < ?1
IsNull 
findByAgeIsNull ˇ­ where x.age is null
IsNotNull,
NotfNiunldlByAge(Is)NotNull ˇ­ where x.age not null
Like 
findByFirstnameLike ˇ­ where x.firstname like ?1
NotLike 
findByFirstnameNotLike ˇ­ where x.firstname not like ?1
StartingWith 
findByFirstnameStartingWitˇ­h where x.firstname like ?1 (parameter
bound with appended %)
EndingWith 
findByFirstnameEndingWithˇ­ where x.firstname like ?1 (parameter
bound with prepended %)
Containing 
findByFirstnameContainingˇ­ where x.firstname like ?1 (parameter
bound wrapped in %)
OrderBy 
findByAgeOrderByLastnameDeˇ­s wchere x.age = ?1 order by x.lastname
desc
Not 
findByLastnameNot ˇ­ where x.lastname <> ?1
In 
findByAgeIn(Collection<Age>
ages)
ˇ­ where x.age in ?1

NotIn 
findByAgeNotIn(Collection<Age>
age)
ˇ­ where x.age not in ?1
True 
findByActiveTrue() ˇ­ where x.active = true
False 
findByActiveFalse() ˇ­ where x.active = false