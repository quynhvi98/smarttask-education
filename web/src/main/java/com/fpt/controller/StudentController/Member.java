package com.fpt.controller.StudentController;

public class Member {

    String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member() {
    }

    @Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Member employee = (Member) object;
            if (this.name.equals(employee.getName())  ) {
                result = true;
            }
        }
        return result;
    }
}
