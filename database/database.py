#!/usr/bin/python3

import psycopg2
import csv

conn = psycopg2.connect("dbname=festival user=postgres host=127.0.0.1")

cur = conn.cursor()


def prepare_database():
    cur.execute('DROP SCHEMA if exists public cascade; '
                'CREATE SCHEMA public '
                'AUTHORIZATION postgres; '
                'GRANT ALL ON SCHEMA public TO postgres; '
                'GRANT ALL ON SCHEMA public TO public; '
                'COMMENT ON SCHEMA public '
                'IS \'standard public schema\';')


def insert_file(filename):
    with open('csv/' + filename + '.csv', newline='\n') as file:
        reader = csv.reader(file, delimiter=';')
        for line in reader:
            query = "insert into " + filename + " values ("
            for i, item in enumerate(line):
                if str(item) == 'null':
                    query += "null, "
                elif item.__class__ == str:
                    query += "'{0}', ".format(item.replace("'", ""))
                else:
                    query += "{0}, ".format(item)
            query = query[:-2]
            query += ");"
            cur.execute(query)


def main():
    prepare_database()

    with open('tables.sql') as tables:
        cur.execute(tables.read())

    with open('indexes.sql') as indexes:
        cur.execute(indexes.read())

    files = ['visitor', 'journalist', 'ticket_type', 'ticket_price', 'location', 'festival_event', 'application',
             'provider', 'sponsor', 'vendor', 'donor', 'area', 'advertisement', 'stage', 'band', 'song',
             'visitor_account', 'timetable_entry', 'product', 'shop', 'sold_in', 'wristband', 'ticket', 'department',
             'employee', 'note', 'employee_note', 'instruction', 'shift', 'employee_shift', 'newsletter',
             'newsletter_application', 'post', 'sale', 'area_access', 'plays']
    for file in files:
        insert_file(file)

    conn.commit()


if __name__ == '__main__':
    main()
