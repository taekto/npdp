# Generated by Django 4.2.4 on 2023-08-14 04:57

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('recommend', '0002_ingredient'),
    ]

    operations = [
        migrations.CreateModel(
            name='Recipe',
            fields=[
                ('recipe_id', models.IntegerField(primary_key=True, serialize=False)),
                ('name', models.DecimalField(decimal_places=4, max_digits=10)),
                ('way', models.IntegerField()),
                ('weight', models.DecimalField(decimal_places=4, max_digits=10)),
                ('calorie', models.DecimalField(decimal_places=4, max_digits=10)),
                ('carbohydrate', models.DecimalField(decimal_places=4, max_digits=10)),
                ('protein', models.DecimalField(decimal_places=4, max_digits=10)),
                ('fat', models.DecimalField(decimal_places=4, max_digits=10)),
                ('salt', models.DecimalField(decimal_places=4, max_digits=10)),
                ('img_small', models.CharField(max_length=255)),
                ('img_big', models.CharField(max_length=255)),
                ('category', models.CharField(max_length=255)),
                ('dish', models.IntegerField()),
            ],
            options={
                'db_table': 'recipe',
                'managed': False,
            },
        ),
    ]
