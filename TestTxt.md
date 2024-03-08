    to perform this tests u need to add them in lendingsystem constructor.
    
    
    time = new Time();
    Member m1 = new Member("M1", "M1@gmail.com", "1234567890", generateId(), time.getDay());
    Member m2 = new Member("M2", "M2@gmail.com", "9876543290", generateId(), time.getDay());
    Member m3 = new Member("M3", "M3@gmail.com", "8247289790", generateId(), time.getDay());
    members.add(m1);
    members.add(m2);
    members.add(m3);
    m1.addCredits(400);

    Item i1 = new Item("I1", "Phone", "Apple Mobile Phone", 50, time.getDay(), generateId());
    Item i2 = new Item("I2", "Phone", "Apple Mobile Phone", 10, time.getDay(), generateId());
    m1.addItem(i1);
    m1.addItem(i2);

    addContract(m1, m3, i2, 2, 5);