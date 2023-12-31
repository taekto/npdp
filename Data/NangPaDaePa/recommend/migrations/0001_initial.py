# Generated by Django 4.2.4 on 2023-08-14 01:15

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='MemberRecommend',
            fields=[
                ('member_recommend_id', models.IntegerField(primary_key=True, serialize=False)),
                ('recipe_id', models.IntegerField()),
                ('similarity', models.DecimalField(decimal_places=2, max_digits=5)),
            ],
            options={
                'db_table': 'member_recommend',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='RecipeIngredient',
            fields=[
                ('recipe_ingredient_id', models.IntegerField(primary_key=True, serialize=False)),
                ('recipe_id', models.IntegerField()),
                ('ingredient_id', models.IntegerField()),
                ('type', models.IntegerField()),
                ('amount', models.CharField(max_length=10)),
                ('unit', models.CharField(max_length=255)),
                ('etc', models.CharField(max_length=255)),
            ],
            options={
                'db_table': 'recipe_ingredient',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='RecipeRecommend',
            fields=[
                ('member_recommend_id', models.IntegerField(primary_key=True, serialize=False)),
                ('recipe_own_id', models.IntegerField()),
                ('recipe_slave_id', models.IntegerField()),
                ('similarity', models.DecimalField(decimal_places=2, max_digits=5)),
            ],
            options={
                'db_table': 'recipe_recommend',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='Refrigerator',
            fields=[
                ('refregirator_id', models.IntegerField(primary_key=True, serialize=False)),
                ('member_id', models.IntegerField()),
                ('ingredient_id', models.IntegerField()),
                ('storage', models.IntegerField()),
                ('amount', models.DecimalField(decimal_places=4, max_digits=10)),
                ('start_date', models.DateField()),
                ('expired_date', models.DateField()),
            ],
            options={
                'db_table': 'refregirator',
                'managed': False,
            },
        ),
    ]
